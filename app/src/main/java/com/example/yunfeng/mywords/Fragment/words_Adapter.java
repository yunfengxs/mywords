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
/**
 * Created by yunfeng on 2018/6/14.
 */

public class words_Adapter extends ArrayAdapter<words_item> {
    private int resourceId;

    public words_Adapter(Context context, int textViewResourceId,
                         List<words_item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        words_item mywords = getItem(position);
        View view;
        ViewHolders viewHolders;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolders = new ViewHolders();

            viewHolders.word = (TextView) view.findViewById(R.id.me_name1);
            viewHolders.meaning = (TextView) view.findViewById(R.id.me_name2);
            viewHolders.read = (ImageView) view.findViewById(R.id.reads);
            view.setTag(viewHolders); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolders = (words_Adapter.ViewHolders) view.getTag(); // 重新获取ViewHolder
        }

        viewHolders.word.setText(mywords.getWords());
        viewHolders.meaning.setText(mywords.getMeaning());
        viewHolders.read.setImageResource(mywords.getImageid());
        return view;

    }

    class ViewHolders {

        TextView word;

        TextView meaning;

        ImageView read;

    }
}
