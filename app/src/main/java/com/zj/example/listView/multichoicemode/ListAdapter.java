package com.zj.example.listView.multichoicemode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * create by zhengjiong
 * Date: 2015-03-22
 * Time: 21:24
 */
public class ListAdapter extends BaseAdapter{
    private static final List<String> mItems =
            Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O");
    private Context mContext;
    private ListView mListView;

    public ListAdapter(Context context, ListView listView){
        mContext = context;
        this.mListView = listView;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public String getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);
        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(mItems.get(position));

        if (mListView.isItemChecked(position)) {
            textView.setBackgroundResource(R.drawable.list_selected_holo_light);
        } else {
            textView.setBackgroundResource(R.drawable.item_background);
        }

        return convertView;
    }
}
