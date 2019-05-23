package com.mingrisoft.kelinelayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.kelinelayout.intf.Http;
import com.mingrisoft.kelinelayout.mod.GetSMS;
import com.mingrisoft.kelinelayout.mod.LoginBean;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private TextView t1,tdiscover;
    private Button b1,b2;
    private ImageView idiscover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notlogin);
        init();
    }


    private void init() {

        t1 = (TextView) findViewById(R.id.click_login);
        t1.setOnClickListener(this);

        b1=  (Button) findViewById(R.id.bn_my_recommend);
        b1.setOnClickListener(this);

        b2=  (Button) findViewById(R.id.bnrecommend);
        b2.setOnClickListener(this);

        tdiscover = (TextView) findViewById(R.id.tv_find);
        tdiscover.setOnClickListener(this);

        idiscover = (ImageView) findViewById(R.id.ib_find);
        idiscover.setOnClickListener(this);

    }

    /*
    点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click_login: //点击登录
                Intent intent = new Intent(this, EnterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bn_my_recommend: //点击进入我的荐股
                Intent intent2 = new Intent(this, EnterMyrecommend.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.bnrecommend: //点击进入我的收藏
                Intent intent3 = new Intent(this, SCActivity.class);
                startActivity(intent3);
                finish();
                break;

            case R.id.tv_find:   //进入发现页面
                Intent intent4 = new Intent(this, DiscoverActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.ib_find:   //进入发现页面
                Intent intent5 = new Intent(this, DiscoverActivity.class);
                startActivity(intent5);
                finish();
                break;

        }
    }


}
