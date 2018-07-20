package com.example.yunfeng.mywords.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yunfeng.mywords.R;

import java.util.List;

public class me_Adapter extends ArrayAdapter<me_items> {

    private int resourceId;

    public me_Adapter(Context context, int textViewResourceId,
                      List<me_items> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        me_items fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage1 = (ImageView) view.findViewById(R.id.me_image1);
            viewHolder.fruitName1 = (TextView) view.findViewById(R.id.me_name1);
           // viewHolder.fruitName2 = (TextView) view.findViewById(R.id.me_name2);
           viewHolder.fruitImage2= (ImageView) view.findViewById(R.id.me_image2);

            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.fruitImage1.setImageResource(fruit.getImageid1());
        viewHolder.fruitName1.setText(fruit.getName1());
        viewHolder.fruitImage2.setImageResource(fruit.getImageid2());
        //viewHolder.fruitName2.setText(fruit.getName2());
        return view;
    }

    class ViewHolder {

        ImageView fruitImage1;

        TextView fruitName1;

        ImageView fruitImage2;

      //  TextView fruitName2;

    }

}