package com.example.olga.growaround.viewcontroller.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;
import com.example.olga.growaround.R;

/**
 * Created by Shay Lavi on 04/03/2017.
 */

public class UserCardAdapter extends BaseAdapter{

    Context context;
    List list;

    public UserCardAdapter(Context context, List list){

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         View view;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_card_main, parent, false);
        } else {
            view = convertView;
        }

        return view;
    }
}

