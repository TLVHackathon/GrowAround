package com.example.olga.growaround.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.olga.growaround.R;

public class UserProfileActivity extends AppCompatActivity {

    LinearLayout lstItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //lstItems = (LinearLayout)findViewById(R.id.lstGiving);


    }
}
