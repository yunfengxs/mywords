package com.example.yunfeng.mywords.Fragment;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yunfeng.mywords.DatabaseHelper;
import com.example.yunfeng.mywords.R;
import com.example.yunfeng.mywords.myApplication;
import com.example.yunfeng.mywords.mydata;
import com.example.yunfeng.mywords.shops;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunfeng on 2018/6/14.
 */

public class showWords extends ListFragment {
    List<words_item> words = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //定义一个数组
        initItems();
        words_Adapter adapter = new words_Adapter(getActivity(), R.layout.words_item, words);
        //绑定适配器时，必须通过ListFragment.setListAdapter()接口，而不是ListView.setAdapter()或其它方法
        setListAdapter(adapter);
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View views = inflater.inflate(R.layout.showwords, container, false);
        views.findViewById(android.R.id.list);
        return views;
    }

    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
    public List<String> selectall(DatabaseHelper db) {

        List<String> words =new ArrayList<>();
        SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cursor = dbs.rawQuery("select Word from words", null);
        // Cursor cursor = dbs.query("words", new String[]{"id"}, "id=?", new String[]{t_name}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                words.add(cursor.getString(cursor.getColumnIndex("Word")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return words;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        words_item title = words.get(position);
        Toast.makeText(getActivity(), "单词释义："+title.meaning,Toast.LENGTH_SHORT).show();
    }



    public int selectnumber(DatabaseHelper db) {
        int num=0;
        SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cursor = dbs.rawQuery("select count(*) from words", null);
        // Cursor cursor = dbs.query("words", new String[]{"id"}, "id=?", new String[]{t_name}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                num=(cursor.getInt(cursor.getColumnIndex("Word")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return num;
    }
    public Long getCount(DatabaseHelper dbs) {
        SQLiteDatabase db = dbs.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*)from words",null);
        cursor.moveToFirst();
        Long count = cursor.getLong(0);
        cursor.close();
        return count;
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
        meaning=meaning.replace(" ","");
        meaning=meaning.replace("\t","");
        if(meaning.equals("no"))
            return "no";
        else
        return meaning.substring(t_name.length(),meaning.length());
    }
   /* public String getMean(DatabaseHelper dbs,String w) {
        SQLiteDatabase db = dbs.getReadableDatabase();
        Cursor cursor = db.rawQuery("select mean from words where Word ="+w,null);
        cursor.moveToFirst();
        String m = cursor.getString(0);
        cursor.close();
        return m;
    }*/
    private void initItems() {
        words.clear();
        myApplication myApp = myApplication.getInstance();
        int i=0;
        List<String> mywords =new ArrayList<>();
        mywords=selectall(myApp.dbhelper);
        //final String ss = mEditText.getText().toString().trim().toLowerCase();
        for ( i = 0; i <getCount(myApp.dbhelper); i++) {
            words_item apple = new words_item(mywords.get(i),selects(myApp.dbhelper,mywords.get(i)),R.drawable.wh);
           // selects(myApp.dbhelper,mywords.get(i));
            words.add(apple);
        }
        i=0;
    }
}

