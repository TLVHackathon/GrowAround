package com.example.olga.growaround.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;
import com.example.olga.growaround.viewcontroller.adapters.MainCardAdapter;

public class UserCardActivity extends AppCompatActivity {

    ListView lstSearching;
    MainCardAdapter mCardAdapter;

    LinearLayout mLinearLayout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);

        Intent intent = getIntent();

        if(intent.hasExtra("details")) {
            Card current = intent.getParcelableExtra("details");
        }

        //replaceTextsOnScreen();

/*
        mCardAdapter = new MainCardAdapter(itemsList, UserCardActivity.this);
        lstSearching = new ListView(UserCardActivity.this);
        lstSearching.setAdapter(mCardAdapter);

        mLinearLayout = (LinearLayout)findViewById(R.id.hsvSearching);
        mLinearLayout.addView(lstSearching);
*/

    }

}
