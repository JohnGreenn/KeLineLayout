package com.mingrisoft.kelinelayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DiscoverActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tme;
    private ImageView ime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        init();

    }

    private void init() {
        tme = (TextView) findViewById(R.id.tv_profile);
        tme.setOnClickListener(this);

        ime = (ImageView) findViewById(R.id.ib_profile);
        ime.setOnClickListener(this);
    }

    /*
    点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_profile:   //进入我的页面
                Intent intent5 = new Intent(this, MainActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.ib_profile:   //进入我的页面
                Intent intent6 = new Intent(this, MainActivity.class);
                startActivity(intent6);
                finish();
                break;
        }
    }
}