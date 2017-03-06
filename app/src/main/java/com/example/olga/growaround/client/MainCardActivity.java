package com.example.olga.growaround.client;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;
import com.example.olga.growaround.viewcontroller.adapters.MainCardAdapter;
import com.example.olga.growaround.viewcontroller.adapters.PropertyCardAdapter;
import com.example.olga.growaround.viewcontroller.views.NoInternetFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class MainCardActivity extends AppCompatActivity {

    MainCardAdapter mainCardAdapter;
    PropertyCardAdapter propertyCardAdapter;
    ListView myListView;
    ArrayList<Card> cardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_main);

        myListView = (ListView) findViewById(R.id.cardListView);

        //startDownload(); after we have a Firebase user

        testData();



        if (myListView != null) {

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Card details = (Card) mainCardAdapter.getItem(position);
                    Intent intent = new Intent(MainCardActivity.this, UserCardActivity.class);
                    intent.putExtra("details", details);
                    startActivity(intent);
                }
            });
        }

    }

    private void testData() {
        new AsyncTask<Void, Void, ArrayList<Card>>(){

            @Override
            protected ArrayList<Card> doInBackground(Void... params) {

                ArrayList<Card> tempCardList = new ArrayList<>();

                for (int i = 0; i < 20; i ++) {
                    Card tempCard = new Card();
                    tempCard.setLocation("Tel-Aviv" + i);
                    tempCard.setUserName("Moshe" + i);
                    tempCardList.add(tempCard);
                }
                return tempCardList;
            }

            @Override
            protected void onPostExecute(ArrayList<Card> cards) {
                super.onPostExecute(cards);

                Collections.reverse(cards);   //new card first

                cardList = cards;

                if (cardList.size() != 0) {
                    mainCardAdapter = new MainCardAdapter(cardList, MainCardActivity.this);
                    myListView.setAdapter(mainCardAdapter);
                }
            }
        }.execute();
    }

    public void logInBtnClick(View view) {
        //Intent intent = new Intent(MainCardActivity.this, LogIn.class);
        //startActivity(intent);
    }






    /*
    private void startDownload() {
        new AsyncTask<Void, Void, ArrayList<Card>>(){
            @Override
            protected ArrayList<Card> doInBackground(Void... voids) {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://findyourpet-9ca26.firebaseio.com/ads/lost.json");
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    Reader reader = new InputStreamReader(inputStream, "UTF-8");

                    Gson gson = new Gson();
                    Map<String, Card> cardsList = gson.fromJson(reader, new TypeToken<Map<String, Card>>(){}.getType());
                    ArrayList<Card> newCard = new ArrayList<>();
                    for (Map.Entry<String, Card> entry : cardsList.entrySet()) {
                        newCard.add(entry.getValue());
                    }
                    return newCard;
                }

                catch (MalformedURLException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
                catch (Exception e){e.printStackTrace();}
                finally {connection.disconnect();}

                return cardList;
            }

            @Override
            protected void onPostExecute(ArrayList<Card> cards) {
                Collections.reverse(cards);   //new card first
                cardList = cards;
                if (cardList.size() != 0) {
                    mainCardAdapter = new MainCardAdapter(cardList, MainCardActivity.this);
                    myListView.setAdapter(mainCardAdapter);
                }
                else {  //if list is empty (no cards or not internet) - alert dialog (pop up window)
                    getSupportFragmentManager().beginTransaction().add(new NoInternetFragment(), getString(R.string.INTERNET)).commitAllowingStateLoss();
                }
            }
        }.execute();
    }
    */

}
