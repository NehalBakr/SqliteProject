package com.example.nehal.sqliteauth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nehal.sqliteauth.R;
import com.example.nehal.sqliteauth.modal.User;

import java.util.List;



public class SearchAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List <User> userList;

    public SearchAdapter (Context context, List <User> customListView){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userList = customListView;

    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder listViewHolder;
        if (convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item, parent ,false);
            listViewHolder.userName = (TextView) convertView.findViewById(R.id.list_item_search);
            convertView.setTag(listViewHolder);
        }else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }
        listViewHolder.userName.setText(userList.get(position).getName());
        return convertView;
    }
    private static class ViewHolder{
        TextView userName;
    }
}
