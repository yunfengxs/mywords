package com.example.yunfeng.mywords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class mydata extends AppCompatActivity implements View.OnClickListener {
    EditText name = null;
    EditText pass1 = null;
    EditText pass2 = null;
    RadioGroup sex = null;
    Button but = null;
    boolean flag = false;
    String names = null;
    String pass = null;
    String passs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydata);
        but = (Button) findViewById(R.id.reg);
        name = (EditText) findViewById(R.id.editText01);
        pass1 = (EditText) findViewById(R.id.editText02);
        pass2 = (EditText) findViewById(R.id.editText03);
        sex = (RadioGroup) findViewById(R.id.sex);
        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mydata.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}