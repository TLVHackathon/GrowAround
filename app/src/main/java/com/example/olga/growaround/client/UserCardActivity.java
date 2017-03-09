package com.example.olga.growaround.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;
import com.example.olga.growaround.manager.model.User;
import com.example.olga.growaround.viewcontroller.adapters.ItemsMapping;
import com.example.olga.growaround.viewcontroller.adapters.MainCardAdapter;
import com.example.olga.growaround.viewcontroller.views.ItemImageView;

import java.util.HashMap;

public class UserCardActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    ItemsMapping itemsMapping;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);

        Intent intent = getIntent();

        if(intent.hasExtra(getString(R.string.card_intent))) {
            itemsMapping = new ItemsMapping();
            Card current = intent.getParcelableExtra(getString(R.string.card_intent));

            TextView username = (TextView)findViewById(R.id.txtUserName);
            username.setText(current.getUserName());
            TextView userDetails = (TextView)findViewById(R.id.txtUserDetails);
            userDetails.setText(current.getUserDetails());
            TextView userLocation = (TextView)findViewById(R.id.txtLocation);
            userLocation.setText(current.getLocation());


            mLinearLayout = (LinearLayout)findViewById(R.id.lstSearching);
            for (Integer user : current.getItemsSearch()) { //for (User user : userReceived) {
                ItemImageView item = new ItemImageView(this);
                //user.getVegetables().get(i);
                item.setImageResource(itemsMapping.getItem(user));
                mLinearLayout.addView(item);
            }

            mLinearLayout = (LinearLayout)findViewById(R.id.lstOffering);
            for (Integer user : current.getItemsOffer()) {
                ItemImageView item = new ItemImageView(this);
                item.setImageResource(itemsMapping.getItem(user));
                mLinearLayout.addView(item);
            }

            mLinearLayout = (LinearLayout)findViewById(R.id.lstGiving);
            for (Integer user : current.getItemsGive()) {
                ItemImageView item = new ItemImageView(this);
                item.setImageResource(itemsMapping.getItem(user));
                mLinearLayout.addView(item);
            }

        }else{
            // Error receiving information the user - go back with an error code.
            finishActivity(-1);
        }
    }

}
