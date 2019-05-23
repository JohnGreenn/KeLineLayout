package com.mingrisoft.kelinelayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.kelinelayout.intf.Http;
import com.mingrisoft.kelinelayout.mod.GetSMS;
import com.mingrisoft.kelinelayout.mod.checkCode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by christ on 19/5/7.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextP,editSMS,gotcode;
    private Button button,SMSBtn,button2,bn_sms;
    private TextView enterText;
    private ImageView returnImage;
    private Http api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.186:8003/kura-service/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Http.class);
    }

    public static OkHttpClient getClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {

                        String SECRECT = "227ca84aad90e172218183e970ee0516";
                        String APPID = "Android-1488435881";

                        int NONCE = ((int) ((Math.random() * 9 + 1) * 100000));
                        String CURTIME = getTime1();

                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("pragma")
                                .cacheControl(CacheControl.FORCE_NETWORK)
                                .header("CLIENT", "android")
                                .header("APPID", APPID)
                                .header("CURTIME", CURTIME)
                                .header("NONCE", NONCE + "")
                                .header("OPENKEY", md5(APPID + NONCE + CURTIME + SECRECT))
//                                .header("UserId",spfapp.UserId().get(""))
                                .build();

                        return chain.proceed(request);
                    }
                })
                .connectTimeout(3,TimeUnit.MINUTES)
                .build();

        return httpClient;

    }

    /*
 md5加密
  */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?",e);
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?",e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for(byte b : hash) {
            if((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    /*
    获取当前时间
    */
    private static String getTime1() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        return t1;
    }

    private void init() {
        editTextP = (EditText) findViewById(R.id.et_phone_num);
        editSMS = (EditText) findViewById(R.id.gotCode);

        button = (Button) findViewById(R.id.bn_immediateRegistration);
        button.setOnClickListener(this);

        enterText = (TextView) findViewById(R.id.tv_enter) ;
        enterText.setOnClickListener(this);


        returnImage = (ImageView) findViewById(R.id.iv_return);
        returnImage.setOnClickListener(this);

        SMSBtn = (Button) findViewById(R.id.getCode);
        SMSBtn.setOnClickListener(this);

        //gotcode = (EditText) findViewById(R.id.gotCode);
        //phone = (EditText) findViewById(R.id.phone);

        bn_sms = (Button) findViewById(R.id.bn_sms_login);
        bn_sms.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.getCode);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bn_immediateRegistration:
                register();
                break;

            case R.id.iv_return:
                returnEnter();
                break;
            case R.id.tv_enter:

                Intent intent2 = new Intent(this,EnterActivity.class);
                startActivity(intent2);
                finish();
                break;


            case R.id.getCode: //获取验证码
                final String username = editTextP.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
                    editTextP.requestFocus();
                }else {
                    Toast.makeText(this, "验证码获取成功", Toast.LENGTH_SHORT).show();
                }
                getSms(editTextP.getText().toString());
                break;


            case R.id.bn_sms_login: //验证码登录
                checkSms(editTextP.getText().toString(),editSMS.getText().toString());
                break;
        }
    }

    private void returnEnter() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

//    public void enterFma() {
//        Intent intent2 = new Intent(this,EnterFmaActivity.class);
//        startActivity(intent2);
//        finish();
//    }



    public void register() {
        final String username = editTextP.getText().toString().trim();
        final String password = editSMS.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {  //当手机号没有输入时
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            editTextP.requestFocus();//使输入框失去焦点
            return;
        } else if (TextUtils.isEmpty(password)) {//当验证码没有输入时
            Toast.makeText(this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
            editSMS.requestFocus();//使输入框失去焦点
            return;
        }
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("正在注册……");
            pd.show();

            new Thread(new Runnable() {
                public void run() {
                    //注册的操作放在此处
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {

                    }
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }
            }).start();

        }
    }

    public void getSms(String phone){
        api.getSms(phone).enqueue(new Callback<GetSMS>() {
            @Override
            public void onResponse(Call<GetSMS> call, Response<GetSMS> response) {
                if(response.body().getResult().getCode()==100){
                    Toast.makeText(RegisterActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSMS> call, Throwable t) {
                Log.i("failure",t.getMessage());
            }
        });


    }

    public void checkSms(String phone,String gotcode) {
        api.checkSms(phone,gotcode).enqueue(new Callback<checkCode>() {
            @Override
            public void onResponse(Call<checkCode> call, Response<checkCode> response) {
                if(response.body().getResult().getCode()==100){
                    Toast.makeText(RegisterActivity.this, "验证码验证成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<checkCode> call, Throwable t) {
                Log.i("failure",t.getMessage());
            }

        });
    }
}