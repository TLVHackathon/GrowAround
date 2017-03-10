package com.example.olga.growaround.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;
import com.example.olga.growaround.viewcontroller.adapters.ItemsMapping;
import com.example.olga.growaround.viewcontroller.views.ItemImageView;

public class RequestActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    ItemsMapping itemsMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);


        Intent intent = getIntent();

        if(intent.hasExtra(getString(R.string.card_intent))) {
            itemsMapping = new ItemsMapping();
            Card current = intent.getParcelableExtra(getString(R.string.card_intent));

            TextView username = (TextView)findViewById(R.id.txtUserName);
            username.setText(current.getUserName());
            TextView userDetails = (TextView)findViewById(R.id.txtUserDetails);
            userDetails.setText(current.getUserDetails());


            mLinearLayout = (LinearLayout)findViewById(R.id.lstSearching);
            for (int itemIndex : current.getItemsSearch()) {
                ItemImageView item = new ItemImageView(this);
                item.setImageResource(itemsMapping.getItemsWrapper(itemIndex).getDrawableResource());
                item.setText(itemsMapping.getItemsWrapper(itemIndex).getName());
                mLinearLayout.addView(item);
            }

            mLinearLayout = (LinearLayout)findViewById(R.id.lstOffering);
            for (int itemIndex : current.getItemsSearch()) {
                ItemImageView item = new ItemImageView(this);
                item.setImageResource(itemsMapping.getItemsWrapper(itemIndex).getDrawableResource());
                item.setText(itemsMapping.getItemsWrapper(itemIndex).getName());
                mLinearLayout.addView(item);
            }

            mLinearLayout = (LinearLayout)findViewById(R.id.lstGiving);
            for (int itemIndex : current.getItemsSearch()) {
                ItemImageView item = new ItemImageView(this);
                item.setImageResource(itemsMapping.getItemsWrapper(itemIndex).getDrawableResource());
                item.setText(itemsMapping.getItemsWrapper(itemIndex).getName());
                mLinearLayout.addView(item);
            }



        }else{
            // Error receiving information the user - go back with an error code.
            finishActivity(-1);
        }
    }
}
