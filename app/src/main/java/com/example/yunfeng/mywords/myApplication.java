package com.example.yunfeng.mywords;

import android.app.Application;
import android.media.AudioManager;
import android.media.SoundPool;


/**
 * Created by yunfeng on 2018/6/14.
 */

public class myApplication extends Application {
    public String appVersion = "v1.0";
    public static myApplication instance;
    public user oneuser=new user(1,"yunfeng");
    @Override
    public void onCreate() {
        super.onCreate();
        initV();
        instance = this;

    }

    public static com.example.yunfeng.mywords.myApplication getInstance(){
        return instance;
    }

    public DatabaseHelper dbhelper = new DatabaseHelper(this, "mydb.db", null, 1);


    public int[] soundID = new int[26];

    SoundPool sp = new SoundPool(26, AudioManager.STREAM_MUSIC, 0);
    public void initV() {
        soundID[0] = sp.load(getApplicationContext(), R.raw.a, 1);
        soundID[1] = sp.load(getApplicationContext(), R.raw.b, 1);
        soundID[2] = sp.load(getApplicationContext(), R.raw.c, 1);
        soundID[3] = sp.load(getApplicationContext(), R.raw.d, 1);
        soundID[4] = sp.load(getApplicationContext(), R.raw.e, 1);
        soundID[5] = sp.load(getApplicationContext(), R.raw.f, 1);
        soundID[6] = sp.load(getApplicationContext(), R.raw.g, 1);
        soundID[7] = sp.load(getApplicationContext(), R.raw.h, 1);
        soundID[8] = sp.load(getApplicationContext(), R.raw.i, 1);
        soundID[9] = sp.load(getApplicationContext(), R.raw.j, 1);
        soundID[10] = sp.load(getApplicationContext(), R.raw.k, 1);
        soundID[11] = sp.load(getApplicationContext(), R.raw.l, 1);
        soundID[12] = sp.load(getApplicationContext(), R.raw.m, 1);
        soundID[13] = sp.load(getApplicationContext(), R.raw.n, 1);
        soundID[14] = sp.load(getApplicationContext(), R.raw.o, 1);
        soundID[15] = sp.load(getApplicationContext(), R.raw.p, 1);
        soundID[16] = sp.load(getApplicationContext(), R.raw.q, 1);
        soundID[17] = sp.load(getApplicationContext(), R.raw.r, 1);
        soundID[18] = sp.load(getApplicationContext(), R.raw.s, 1);
        soundID[19] = sp.load(getApplicationContext(), R.raw.t, 1);
        soundID[20] = sp.load(getApplicationContext(), R.raw.u, 1);
        soundID[21] = sp.load(getApplicationContext(), R.raw.v, 1);
        soundID[22] = sp.load(getApplicationContext(), R.raw.w, 1);
        soundID[23] = sp.load(getApplicationContext(), R.raw.x, 1);
        soundID[24] = sp.load(getApplicationContext(), R.raw.y, 1);
        soundID[25] = sp.load(getApplicationContext(), R.raw.z, 1);
    }
}
