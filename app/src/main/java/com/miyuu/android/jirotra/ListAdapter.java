package com.miyuu.android.jirotra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    int resourcedId;
    String[] items;
    ArrayList<String> list;
    CheckBox checkBox;
    TextView itemText;

    public ListAdapter(Context context, int resourcedId, String[] items) {

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
        this.items = items;
//        this.context = context;
//        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(resourcedId, parent, false);

        itemText = convertView.findViewById(R.id.itemTextView);
        checkBox = convertView.findViewById(R.id.itemCheckBox);

        itemText.setText(list.get(position));

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

}
