package com.example.olga.growaround.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;

public class UserCardActivity extends AppCompatActivity {
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);

        Intent intent = getIntent();

        if(intent.hasExtra("details")) {
            Card current = intent.getParcelableExtra("details");
        }


    }

}
