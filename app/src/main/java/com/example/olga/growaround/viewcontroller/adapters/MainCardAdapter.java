package com.example.olga.growaround.viewcontroller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olga on 3/4/17.
 */

public class MainCardAdapter extends BaseAdapter{

    Context context;
    ArrayList<Card> list;

    public MainCardAdapter(ArrayList<Card> lists, Context context){
        this.list = lists;
        this.context = context;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        if (convertView == null){
            row = LayoutInflater.from(context).inflate(R.layout.row_card_main, parent, false);
        }
        else {
            row = convertView;
        }

        TextView userNameTextView = (TextView) row.findViewById(R.id.userNameTextView);
        TextView locationTextView = (TextView) row.findViewById(R.id.locationDataTextView);
        LinearLayout vegetablesLinearLayout = (LinearLayout) row.findViewById(R.id.vegetablesLinearView);


        Card current = (Card) getItem(position);

        List<String> vegList = current.getVegetables();

        userNameTextView.setText(current.getUserName());
        locationTextView.setText(current.getLocation());
        ///vegetablesLinearLayout.set

        //for (int i = 0; i < vegList.size(); i++){
            //vegetablesLinearLayout.addView(vegList.get(i))
       // }



     return row;
    }
}
