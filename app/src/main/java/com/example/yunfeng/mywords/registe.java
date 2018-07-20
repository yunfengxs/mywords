package com.example.yunfeng.mywords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by yunfeng on 2018/6/18.
 */

public class registe extends AppCompatActivity implements  View.OnClickListener {
    EditText name=null;
    EditText pass1=null;
    EditText pass2=null;
    RadioGroup sex=null;
    Button but=null;
    boolean flag=false;
    String names=null;
    String pass=null;
    String passs=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        but=(Button) findViewById(R.id.reg);
        name=(EditText) findViewById(R.id.editText01);
        pass1=(EditText) findViewById(R.id.editText02);
        pass2=(EditText) findViewById(R.id.editText03);
        sex=(RadioGroup) findViewById(R.id.sex);
        but.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        names = name.getText().toString().trim();
        pass  =pass1.getText().toString().trim();
        passs  =pass2.getText().toString().trim();
        if(!pass.equals(passs)){
            Toast.makeText(registe.this, "两次输入的密码不一致",Toast.LENGTH_SHORT).show();
            return ;
        }
        else
        {
            Intent intent = new Intent(registe.this,MainActivity.class);
                startActivity(intent);
            finish();
        }
    }
}
