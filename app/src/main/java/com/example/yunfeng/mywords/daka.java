package com.example.yunfeng.mywords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class daka extends AppCompatActivity {
    private DatePicker datePicker;
    private int        year;
    private int        month;//月份是从0开始算的.
    private int        day;
    DatePicker mys=null;
    Button aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka);
        mys=(DatePicker) findViewById(R.id.datepicker);
        aa=(Button) findViewById(R.id.myb);
        initData();
    }


    public void initData(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        Log.d("ssss", String.valueOf(year)+String.valueOf(month)+String.valueOf(day));
       /* datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(daka.this, year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
    public void cancel(View v){
        Toast.makeText(this, "打卡成功", Toast.LENGTH_SHORT).show();
    }
    public void confirm(View v){
        Toast.makeText(this, "打卡成功", Toast.LENGTH_SHORT).show();
    }
}
