package com.mingrisoft.kelinelayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EnterMyrecommend extends AppCompatActivity implements View.OnClickListener{

    private ImageView r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        init();
    }

    private void init() {
        r1 = (ImageView) findViewById(R.id.back_notlogin);
        r1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_notlogin:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
