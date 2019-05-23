package com.mingrisoft.kelinelayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.kelinelayout.intf.Http;
import com.mingrisoft.kelinelayout.mod.LoginBean;

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


public class EnterActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView textViewR ,btn2,editCode,editPerson;
    private Button btn,weixin,bn_login;
    private boolean autoLogin = false;
    public static String currentUsername;
    private String currentPassword;
    private boolean progressShow;
    private ImageView im1;
    private EditText phone,pword;
    private Http api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.186:8003/kura-service/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Http.class);
    }

    private void init() {

//        btn = (Button) findViewById(R.id.login);
//        btn.setOnClickListener(this);
        bn_login =(Button) findViewById(R.id.loginbn);
        bn_login.setOnClickListener(this);

        im1 = (ImageView) findViewById(R.id.back_home) ;
        im1.setOnClickListener(this);

        btn2 = (TextView) findViewById(R.id.bn_registrate);
        btn2.setOnClickListener(this);


        phone = (EditText) findViewById(R.id.phone);
        pword =(EditText) findViewById(R.id.pass);


        //editCode = (TextView) findViewById(R.id.et_password);
        //editPerson = (TextView) findViewById(R.id.et_username);

        weixin = (Button) findViewById(R.id.iv_weixin_login);
        weixin.setOnClickListener(this);

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
                               //.header("UserId",spfapp.UserId().get(""))
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


    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbn:  //登录按钮
                //login(v);
                userlogin(phone.getText().toString(),pword.getText().toString());
                break;

            case R.id.bn_registrate:  //还没注册
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.back_home:  //返回主界面
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                finish();
                break;

            case R.id.iv_weixin_login:  //微信登录
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public void userlogin(String phone,String pword) {

        api.userLogin(phone,pword).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                Toast.makeText(EnterActivity.this, "账号="+response.body().getData().getList().getUSERNAME()+"登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.i("cc",t.getMessage());
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (autoLogin) {
//            return;
//        }
//    }

    /**
     * 登录
     */
//    public void login(View view) {
//
//        currentUsername = phone.getText().toString().trim(); //去除空格，获取手机号
//        currentPassword = pword.getText().toString().trim();  //去除空格，获取密码
//
//        if (TextUtils.isEmpty(currentUsername)) { //判断手机号是不是为空
//            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(currentPassword)) {  //判断密码是不是空
//            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressShow = true;
//        final ProgressDialog pd = new ProgressDialog(EnterActivity.this);  //初始化等待动画
//        /**
//         * 设置监听
//         * */
//        pd.setCanceledOnTouchOutside(false);
//        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
//
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                progressShow = false;   //设置Boolean值为false
//            }
//        });
//        pd.setMessage("正在登录....");  //等待动画的标题
//        pd.show();  //显示等待动画
//
//        new Thread(new Runnable() {
//            public void run() {
//                //在此处睡眠两秒
//                try {
//                    Thread.sleep(2000);  //在此处睡眠两秒
//                } catch (InterruptedException e) {
//                }
//
//                /**
//                 * 两秒之后
//                 * */
//                pd.dismiss();    //等待条消失
//                Intent intent = new Intent(EnterActivity.this, MainActivity.class);  //进入主界面
//                startActivity(intent);  //开始跳转
//                finish();  //finish掉此界面
//            }
//        }).start();  //开始线程
//    }




}