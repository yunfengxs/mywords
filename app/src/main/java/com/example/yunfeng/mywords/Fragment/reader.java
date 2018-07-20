package com.example.yunfeng.mywords.Fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yunfeng.mywords.DatabaseHelper;
import com.example.yunfeng.mywords.R;
import com.example.yunfeng.mywords.myApplication;
import com.example.yunfeng.mywords.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

/**
 * Created by yunfeng on 2018/6/14.
 */

public class reader extends Fragment {
    myApplication myApp = myApplication.getInstance();
    boolean flags = true;
    Button mButton = null;
    Button mButton1 = null;
    Button mButton2 = null;
    Button mButton3 = null;
    Button mButton4 = null;
    TextView mTextView = null;
    EditText mEditText = null;
    Context mContext = null;

    DatabaseHelper dbhelper=myApp.dbhelper;
    int[] soundID = myApp.soundID;
    public user myuser=myApp.oneuser;
    SoundPool sp = new SoundPool(26, AudioManager.STREAM_MUSIC, 0);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.readerclass, container, false);
        return view;
    }
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

       // setContentView(R.layout.readerclass);
        mButton = (Button)     getActivity()  .  findViewById(R.id.button);
        mButton1 = (Button)    getActivity()  .   findViewById(R.id.button1);
        mButton2 = (Button)    getActivity()   .  findViewById(R.id.button2);
        mButton3 = (Button)    getActivity()  .   findViewById(R.id.button3);
       // mButton4 = (Button)    getActivity()   .  findViewById(R.id.button4);
        mTextView = (TextView) getActivity()  . findViewById(R.id.textView);
        mEditText = (EditText) getActivity()  . findViewById(R.id.input);
        initV();
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //调用方法
                final String ss = mEditText.getText().toString().trim().toLowerCase();
                if (selects(myApp.dbhelper, ss) != null)
                    mTextView.setText(selects(myApp.dbhelper, ss));
                else
                    LoadImage(ss);
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                read_word();
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textTask();
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flags = !flags;
            }
        });
      /*  mButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String aa="使用说明：\n1.输入单词，点击查询，可以查看单词释义；\n2.输入单词，点击读单词，则开始读单词；\n3.点击读全部，则开始读所有单词，点击停止，在读单词读完停止；\n4.BUG联系云峰：tel:15317220302    qq:1575987058\n5.注：有时服务器关闭，无法使用。";
                mTextView.setText(aa);
            }
        });
*/
    }
    public void delay(int num) {
        try {
            Thread.currentThread().sleep(num);//阻断0.6秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void read_word() {
        MediaPlayer media = new MediaPlayer();
     //   String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        final String ss = mEditText.getText().toString().trim().toLowerCase();
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (ContextCompat.checkSelfPermission(getActivity(), str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/zwords/", ss + ".mp3");
        if (file.exists()) {
            try {
                //Log.d("make",path);
                media.setDataSource(file.getPath());
                media.prepare();
                media.start();
                delay(800);
                media.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                media.release();
                downloadFile1(ss);
                media.setDataSource(file.getPath());
                media.prepare();
                media.start();
                delay(800);
                media.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void read_zimu() {
        String ss = mEditText.getText().toString().trim().toLowerCase();
        if (ss != "" && ss != null)
            for (int i = 0; i < ss.length(); i++) {
                sp.play(soundID[((int) (ss.charAt(i) - 97))], 0.8f, 0.8f, 1, 0, 1.0f);
                delay(800);
            }
    }

    public void read_zimu(String sss) {
        String ss = sss.trim().toLowerCase();
        if (ss != "" && ss != null)
            for (int i = 0; i < ss.length(); i++) {
                sp.play(soundID[((int) (ss.charAt(i) - 97))], 0.8f, 0.8f, 1, 0, 1.0f);
                delay(800);
            }
    }

    public void read_word(String sss) {
        MediaPlayer media = new MediaPlayer();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String ss = sss.trim().toLowerCase();
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (ContextCompat.checkSelfPermission(getActivity(), str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/zwords/", ss + ".mp3");
        if (file.exists()) {
            try {
                media.setDataSource(file.getPath());
                media.prepare();
                media.start();
                delay(800);
                media.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                downloadFile1(ss);
                media.setDataSource(file.getPath());
                media.prepare();
                media.start();
                delay(800);
                media.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public int selectsnumber(DatabaseHelper db, int ids) {

        int number=0;
        SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cursor = dbs.rawQuery("select count(*) from words where id="+ids, null);
       // Cursor cursor = dbs.query("words", new String[]{"id"}, "id=?", new String[]{t_name}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                number = cursor.getInt(cursor.getCount());
            } while (cursor.moveToNext());
        }
        cursor.close();
        return number;
    }
    public String selects(DatabaseHelper db, String t_name) {
        String meaning = null;
        SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cursor = dbs.query("words", new String[]{"mean"}, "Word=?", new String[]{t_name}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                meaning = cursor.getString(cursor.getColumnIndex("mean"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return meaning;
    }

    public void downloadFile1(final String ss) {
        new Thread() {
            public void run() {
                super.run();
                try {
                    //下载路径，如果路径无效了，可换成你的下载路径
                    String url = "https://fanyi.baidu.com/gettts?lan=en&text=" + ss + "&spd=3&source=web";
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zwords";

                    final long startTime = System.currentTimeMillis();
                    //Log.i("DOWNLOAD","startTime="+startTime);
                    //下载函数
                    String filename = ss + ".mp3";//url.substring(url.lastIndexOf("/") + 1);
                    //获取文件名
                    URL myURL = new URL(url);
                    URLConnection conn = myURL.openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    int fileSize = conn.getContentLength();//根据响应获取文件大小
                    if (fileSize <= 0) throw new RuntimeException("无法获知文件大小 ");
                    if (is == null) throw new RuntimeException("stream is null");
                    File file1 = new File(path);
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    //把数据存入路径+文件名
                    FileOutputStream fos = new FileOutputStream(path + "/" + filename);
                    byte buf[] = new byte[1024];
                    int downLoadFileSize = 0;
                    do {
                        //循环读取
                        int numread = is.read(buf);
                        if (numread == -1) {
                            break;
                        }
                        fos.write(buf, 0, numread);
                        downLoadFileSize += numread;
                        //更新进度条
                    } while (true);
                    Log.i("DOWNLOAD", "download success");
                    Log.i("DOWNLOAD", "totalTime=" + (System.currentTimeMillis() - startTime));
                    is.close();
                } catch (Exception ex) {
                    Log.e("DOWNLOAD", "error: " + ex.getMessage(), ex);
                }
            }
        }.start();
    }

    public void initV() {
        soundID[0] = sp.load(getActivity(), R.raw.a, 1);
        soundID[1] = sp.load(getActivity(), R.raw.b, 1);
        soundID[2] = sp.load(getActivity(), R.raw.c, 1);
        soundID[3] = sp.load(getActivity(), R.raw.d, 1);
        soundID[4] = sp.load(getActivity(), R.raw.e, 1);
        soundID[5] = sp.load(getActivity(), R.raw.f, 1);
        soundID[6] = sp.load(getActivity(), R.raw.g, 1);
        soundID[7] = sp.load(getActivity(), R.raw.h, 1);
        soundID[8] = sp.load(getActivity(), R.raw.i, 1);
        soundID[9] = sp.load(getActivity(), R.raw.j, 1);
        soundID[10] = sp.load(getActivity(), R.raw.k, 1);
        soundID[11] = sp.load(getActivity(), R.raw.l, 1);
        soundID[12] = sp.load(getActivity(), R.raw.m, 1);
        soundID[13] = sp.load(getActivity(), R.raw.n, 1);
        soundID[14] = sp.load(getActivity(), R.raw.o, 1);
        soundID[15] = sp.load(getActivity(), R.raw.p, 1);
        soundID[16] = sp.load(getActivity(), R.raw.q, 1);
        soundID[17] = sp.load(getActivity(), R.raw.r, 1);
        soundID[18] = sp.load(getActivity(), R.raw.s, 1);
        soundID[19] = sp.load(getActivity(), R.raw.t, 1);
        soundID[20] = sp.load(getActivity(), R.raw.u, 1);
        soundID[21] = sp.load(getActivity(), R.raw.v, 1);
        soundID[22] = sp.load(getActivity(), R.raw.w, 1);
        soundID[23] = sp.load(getActivity(), R.raw.x, 1);
        soundID[24] = sp.load(getActivity(), R.raw.y, 1);
        soundID[25] = sp.load(getActivity(), R.raw.z, 1);
    }

    public void inserts(DatabaseHelper db, String t_name, String w_name, String m_name,int id,int num) {
        SQLiteDatabase dbss = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("Word", w_name);
        values.put("mean", m_name);
        values.put("id", id);
        values.put("num",num);
        dbss.insert(t_name, null, values); // 插入第一条数据
        values.clear();
    }

    public void LoadImage(final String bb) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        new Thread() {
            public void run() {
                super.run();
                Looper.prepare();
                try {
                    Socket socket = new Socket("120.79.211.67", 3000);
                    OutputStream os = socket.getOutputStream();
                    os.write(bb.getBytes());
                    os.flush();
                    socket.shutdownOutput();
                    InputStream iss = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(iss);
                    BufferedReader br = new BufferedReader(isr);
                    final String ssss = br.readLine();
                    String s = null;
                    final StringBuffer sb = new StringBuffer();
                    //if(myuser.getId()!= 0);
                    final  int shuliang=0;//selectsnumber(dbhelper,myuser.getId());

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(ssss);
                            inserts(dbhelper, "words", bb, getmean(ssss), myuser.getId(),shuliang);
                            mTextView.setText(selects(dbhelper, ssss));
                      }
                    });
                    br.close();
                    isr.close();
                    os.close();
                    socket.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String getmean(String ss) {
        if(ss.equals("no")){
            return ss;
        }
        String sss = ss.substring(0, ss.indexOf("&&"))
                .replace("英", "\n\n英")
                .replace("n.", "\nn.")
                .replace("adj.", "\nadj.")
                .replace("adv.", "\nadv.")
                .replace("vt.", "\nvt.")
                .replace("vi.", "\nvi.")
                .replace("[其他]", "\n\n[其他]")
                .replace("第三", "\n第三")
                .replace("复数", "\n复数")
                .replace("现在分词", "\n现在分词")
                .replace("过去式", "\n过去式")
                .replace("过去分词", "\n过去分词")
                .replace("比较级", "\n比较级")
                .replace("最高级", "\n最高级");
        return sss;
    }

    public int geturl(String ss) {
        return (ss.substring(ss.indexOf("&&") + 2) == null ? 0 : 1);
    }
    public void textTask(){
        flags=true;
        final android.os.Handler handler=new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                mTextView.setText(msg.getData().getString("a"));
                super.handleMessage(msg);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                String meaning;
                SQLiteDatabase dbs = dbhelper.getWritableDatabase();
                Cursor cursor = dbs.query("words", new String[]{"Word"}, null, null, null, null, null);
                if(cursor==null) {
                    Message msg=new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("a","单词表为空");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                if (cursor.moveToFirst()) {
                    do {
                        Message msg=new Message();
                        Bundle bundle = new Bundle();
                        if (flags != false) {
                            meaning = cursor.getString(cursor.getColumnIndex("Word"));
                            if (meaning != null) {
                                bundle.putString("a",selects(dbhelper, meaning));
                                msg.setData(bundle);
                                handler.sendMessage(msg);
                                delay(meaning.length() * 130);
                                read_word(meaning);
                                delay(meaning.length() * 30);
                                read_zimu(meaning);
                                delay(meaning.length() * 70);
                                read_word(meaning);
                                delay(meaning.length() * 30);
                            }
                        }
                        else{
                            msg.arg1=1;
                            handler.sendMessage(msg);
                        }
                    } while (cursor.moveToNext() && flags == true);
                }
                else{
                    Message msg=new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("a","单词表为空");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                cursor.close();
            }
        }).start();
    }
    @Override
    public void onStop() {
        flags=false;
        super.onStop();
    }
}

