package com.example.olga.growaround.client;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;
import com.example.olga.growaround.manager.model.User;
import com.example.olga.growaround.viewcontroller.adapters.MainCardAdapter;
import com.example.olga.growaround.viewcontroller.views.ForRegisteredDialogFragment;
import com.example.olga.growaround.viewcontroller.views.NoInternetDialogFragment;
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
import java.util.HashMap;
import java.util.Map;

public class MainCardActivity extends AppCompatActivity {

    MainCardAdapter mainCardAdapter;
    ListView myListView;
    ArrayList<Card> cardList = new ArrayList<>();
    private LocationManager mLocationManager;

    int[] userReceived = {0, 1, 2};


    private final static int PERMISSIONS_REQUEST_LOCATION = 999;
    private final static int GPS_REQUEST = 9999;

    private HashMap<Integer, Integer> mUserVariablesToVegetables = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_main);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        myListView = (ListView) findViewById(R.id.cardListView);

        //startDownload(); after we have a Firebase user
        checkIfUserRegistered();
        startDownload();
        //testData();

        if (myListView != null) {
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (checkIfUserRegistered()) {

                        Card cardIntent = (Card) mainCardAdapter.getItem(position);

                        //Toast.makeText(getBaseContext(), cardIntent.getUserName(), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainCardActivity.this, UserCardActivity.class);
                        intent.putExtra(getString(R.string.card_intent), cardIntent);
                        startActivity(intent);

                    } else {    //open a Log in activity

                        getSupportFragmentManager().beginTransaction().add(new ForRegisteredDialogFragment(), getString(R.string.INTERNET)).commitAllowingStateLoss();
                    }
                }
            });
       }
    }

    private boolean checkIfUserRegistered() {
        //////////
        return true;
    }


    public void buildCardList(){
        mainCardAdapter = new MainCardAdapter(cardList, MainCardActivity.this);
        myListView.setAdapter(mainCardAdapter);
    }
    public void logInBtnClick(View view) {
        //Intent intent = new Intent(MainCardActivity.this, LogIn.class);
        //startActivity(intent);
    }
    public void locationBtnClick(View view) {
        checkLocationPermission();
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) { // checks if GPS enabled
            openGpsSettings();
            return false;
        } else {
            Collections.shuffle(cardList);
            buildCardList();
            //mainCardAdapter = new MainCardAdapter(cardList, MainCardActivity.this);
            //myListView.setAdapter(mainCardAdapter);
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // If permission approved
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    //if (mGoogleApiClient == null) {
                    //    buildGoogleApiClient();
                    //}

                    if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        openGpsSettings();
                    }

                   // mMap.setMyLocationEnabled(true);
                    /////////////////////////////////////////////

                    Collections.shuffle(cardList);
                    buildCardList();
                    //mainCardAdapter = new MainCardAdapter(cardList, MainCardActivity.this);
                    //myListView.setAdapter(mainCardAdapter);

                }

            } else {

                // If permission was denied
                Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openGpsSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, GPS_REQUEST);
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
                    tempCard.setItemsGive(userReceived);
                    tempCard.setItemsOffer(userReceived);
                    tempCard.setItemsSearch(userReceived);
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
                    buildCardList();
                }
                else {  //if list is empty (no cards or not internet) - alert dialog (pop up window)
                    getSupportFragmentManager().beginTransaction().add(new NoInternetDialogFragment(), getString(R.string.INTERNET)).commitAllowingStateLoss();
                }
            }
        }.execute();
    }

    private void startDownload() {
        new AsyncTask<Void, Void, ArrayList<Card>>(){
            @Override
            protected ArrayList<Card> doInBackground(Void... voids) {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://growaround-6dda8.firebaseio.com/users.json");
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    Reader reader = new InputStreamReader(inputStream, "UTF-8");

                    Gson gson = new Gson();
                    //Map<String, Card> cardsList = gson.fromJson(reader, new TypeToken<Map<String, Card>>(){}.getType());

                    ArrayList<User> cardList = gson.fromJson(reader, new TypeToken<ArrayList<User>>(){}.getType());

                    ArrayList<Card> newCard = new ArrayList<>();
                    //for (Map.Entry<String, Card> entry : cardsList.entrySet()) {
                     //   newCard.add(entry.getValue());
                    //}

                    
                   for (int i = 0; i < cardList.size(); i++){
                       newCard.add(cardList.get(i).getCard());
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
                super.onPostExecute(cards);

                Collections.reverse(cards);   //new card first
                cardList = cards;
                if (cardList.size() != 0) {
                    buildCardList();
                }
                else {  //if list is empty (no cards or not internet) - alert dialog (pop up window)
                    getSupportFragmentManager().beginTransaction().add(new NoInternetDialogFragment(), getString(R.string.INTERNET)).commitAllowingStateLoss();
                }
            }
        }.execute();
    }

}


/*
public static void postToServer(final String s, final URL url) {
            new Thread() {
                @Override
                public void run() {
                    HttpURLConnection connection = null;
                    try {
                        //URL url = new URL("https://findyourpet-9ca26.firebaseio.com/ads.json");
                        HttpURLConnection.setFollowRedirects(false);    //-------
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
 */