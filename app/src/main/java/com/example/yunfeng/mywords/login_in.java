package com.example.yunfeng.mywords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class login_in extends AppCompatActivity {
    Button but1=null;
    Button but2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        but1=(Button) findViewById(R.id.login_in_bu);
        but2=(Button) findViewById(R.id.zhuce);
        but1.setOnClickListener(new View.OnClickListener() {  //监听Button
            public void onClick(View arg0) {
                Intent intent = new Intent(login_in.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {  //监听Button
            public void onClick(View arg0) {
                Intent intent = new Intent(login_in.this,registe.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
//pxl
//111111