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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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



    public static void postToServer(final String s, final URL url) {
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    //URL url = new URL("https://findyourpet-9ca26.firebaseio.com/ads.json");
                    HttpURLConnection.setFollowRedirects(false);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    //connection.setRequestProperty("Content-Type", "application/json");

                    connection.setConnectTimeout(5000); //set timeout to 5 seconds
                    connection.setReadTimeout(5000);

                    OutputStream outputStream = connection.getOutputStream();   //write
                    outputStream.write(s.getBytes("UTF-8"));
                    connection.connect();

                    outputStream.close();
                    InputStream inputStream = new BufferedInputStream(connection.getInputStream());  //read

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                    }
                    else { inputStream = connection.getErrorStream(); }
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                        StringBuilder stringBuilder = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) { stringBuilder.append(line + "\n"); }

                        inputStream.close();
                        reader.close();

                        String response = stringBuilder.toString();
                    }
                    catch (Exception e) { e.printStackTrace(); }
                    //return response ;
                }

                catch (java.net.SocketTimeoutException e) {e.printStackTrace();}
                catch (java.io.IOException e) {e.printStackTrace();}

                finally {
                    if(connection!=null) { connection.disconnect();}
                }
            }
        }.start();
    }
}
