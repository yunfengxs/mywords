package com.example.yunfeng.mywords.Fragment;


import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yunfeng.mywords.MainActivity;
import com.example.yunfeng.mywords.R;
import com.example.yunfeng.mywords.daka;
import com.example.yunfeng.mywords.mydata;
import com.example.yunfeng.mywords.registe;
import com.example.yunfeng.mywords.shops;

import java.util.ArrayList;
        import java.util.List;


/**
 * Created by yunfeng on 2018/4/11.
 */
/**
 * 此class完成me界面
 */

public class myUser extends ListFragment {
    List<me_items> fruitList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //定义一个数组
        initItems();
        me_Adapter adapter = new me_Adapter(getActivity(), R.layout.me_items, fruitList);
        //绑定适配器时，必须通过ListFragment.setListAdapter()接口，而不是ListView.setAdapter()或其它方法
        setListAdapter(adapter);
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View views = inflater.inflate(R.layout.me, container, false);
        views.findViewById(android.R.id.list);
        return views;
    }


    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        me_items title = fruitList.get(position);
       // Toast.makeText(getActivity(), "您点击的是："+title.getName1(),Toast.LENGTH_SHORT).show();
        switch(title.getName1())
        {
            case "我的资料":
                Intent intent1 = new Intent(getActivity(),mydata.class);
                startActivity(intent1);
                break;
            case "今日打卡":
                ;Intent intent2 = new Intent(getActivity(),daka.class);
                startActivity(intent2);
                break;
            case "商城":
                Intent intent3 = new Intent(getActivity(),shops.class);
                startActivity(intent3);
                ;
                break;
            case "给个好评":
                ;
                break;
            case "分享本软件":
                ;
                break;
            case "设置":
                ;
                break;
        }
    }

    private void initItems() {
        fruitList.clear();
        for (int i = 0; i < 1; i++) {
            me_items apple = new me_items("我的资料", "", R.drawable.face, R.drawable.ri);
            fruitList.add(apple);
            me_items banana = new me_items("今日打卡", "", R.drawable.day, R.drawable.ri);
            fruitList.add(banana);
            me_items orange = new me_items("商城", "", R.drawable.shop, R.drawable.ri);
            fruitList.add(orange);
            me_items orange1 = new me_items("给个好评", "", R.drawable.love, R.drawable.ri);
            fruitList.add(orange1);
            me_items orange2 = new me_items("分享本软件", "", R.drawable.share, R.drawable.ri);
            fruitList.add(orange2);
            me_items orange3 = new me_items("设置", "", R.drawable.set, R.drawable.ri);
            fruitList.add(orange3);
        }
    }
}

