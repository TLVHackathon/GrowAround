package com.example.olga.growaround.client;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.olga.growaround.R;

public class LogInActivity extends AppCompatActivity {

    int flagUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void facebookConnectionBtn(View view) {
        flagUser = 1;

        Intent intent = new Intent(LogInActivity.this, MainCardActivity.class);
        intent.putExtra("flag", flagUser);
        startActivity(intent);
    }

    public void guestBtn(View view) {
        flagUser = 2;

        Intent intent1 = new Intent(LogInActivity.this, MainCardActivity.class);
        intent1.putExtra("flag", flagUser);
        startActivity(intent1);
    }


}
