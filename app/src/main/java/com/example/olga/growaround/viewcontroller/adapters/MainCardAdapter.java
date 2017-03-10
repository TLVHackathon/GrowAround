package com.example.olga.growaround.viewcontroller.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;
import com.example.olga.growaround.viewcontroller.views.ItemImageView;

import java.util.ArrayList;

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
        return position;
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

        ItemsMapping itemsMapping = new ItemsMapping();
        Card current = (Card) getItem(position);



        TextView userNameTextView = (TextView) row.findViewById(R.id.userNameTextView);
        TextView locationTextView = (TextView) row.findViewById(R.id.locationDataTextView);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Alef-Regular.ttf");
        locationTextView.setTypeface(typeface);

        Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "fonts/Alef-Bold.ttf");
        userNameTextView.setTypeface(typeface1);

        LinearLayout vegetablesLinearLayout = (LinearLayout) row.findViewById(R.id.vegetablesLinearView);


        if (vegetablesLinearLayout.getChildCount() == 0) {
            for (int itemIndex : current.getItemsSearch()) { //for (User user : userReceived) {
                ItemImageView item = new ItemImageView(context);//user.getVegetables().get(i);
                item.setImageResource(itemsMapping.getItemsWrapper(itemIndex).getDrawableResource());
                vegetablesLinearLayout.addView(item);
            }
        }

        userNameTextView.setText(current.getUserName());
        locationTextView.setText(current.getLocation());

     return row;
    }
}
