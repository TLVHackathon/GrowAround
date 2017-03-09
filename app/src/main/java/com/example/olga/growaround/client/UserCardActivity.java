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
import com.example.olga.growaround.viewcontroller.adapters.MainCardAdapter;
import com.example.olga.growaround.viewcontroller.views.ItemImageView;

import java.util.HashMap;

public class UserCardActivity extends AppCompatActivity {

    ListView lstSearching;
    LinearLayout mLinearLayout;
    MainCardAdapter mCardAdapter;
    Integer[] userReceived = {0, 3, 5};

    private HashMap<Integer, Integer> mUserVariablesToVegetables = new HashMap<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);

        mUserVariablesToVegetables.put(0, R.drawable.cherry_tomato);
        mUserVariablesToVegetables.put(3, R.drawable.hasa);
        mUserVariablesToVegetables.put(5, R.drawable.nana);

        Intent intent = getIntent();

        if(intent.hasExtra("details")) {
            Card current = intent.getParcelableExtra("details");

            TextView username = (TextView)findViewById(R.id.txtUserName);
            username.setText(current.getUserName());

            mLinearLayout = (LinearLayout)findViewById(R.id.hsvSearching);
            for (Integer user : userReceived) { //for (User user : userReceived) {
                ItemImageView item = new ItemImageView(this);
                //user.getVegetables().get(i);
                item.setImageResource(mUserVariablesToVegetables.get(user));
                mLinearLayout.addView(item);
            }
/**/
            /*
            mCardAdapter = new MainCardAdapter(itemsList, UserCardActivity.this);
*/

        }else{
            // Error receiving information the user - go back with an error code.
            finishActivity(-1);
        }
    }

}
