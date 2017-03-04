package com.example.olga.growaround.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.olga.growaround.R;
import com.example.olga.growaround.manager.model.Card;
import com.example.olga.growaround.viewcontroller.adapters.MainCardAdapter;
import com.example.olga.growaround.viewcontroller.adapters.PropertyCardAdapter;

import java.util.ArrayList;

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



    }



    /* test from old projects

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);
        myListViewF = (ListView) findViewById(R.id.listViewFound);

        startDownloadFound();

        if (myListViewF!=null) {
            myListViewF.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Ad details = (Ad) foundCustomAdapter.getItem(position);
                    Intent intent = new Intent(FoundActivity.this, FoundAdPropertyActivity.class);
                    intent.putExtra("detailsf", details);
                    //intent.putExtra("userf", details.adOwner);
                    intent.putExtra("userf", details.getAdOwner());
                    startActivity(intent);
                }
            });
        }
    }
    private void startDownloadFound() {
        new AsyncTask<Void, Void, ArrayList<Ad>>(){
            @Override
            protected ArrayList<Ad> doInBackground(Void... voids) {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://findyourpet-9ca26.firebaseio.com/ads/lost.json");
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    Reader reader = new InputStreamReader(inputStream, "UTF-8");

                    Gson gson = new Gson();
                    Map<String, Ad> myAds = gson.fromJson(reader, new TypeToken<Map<String, Ad>>(){}.getType());
                    ArrayList<Ad> myAdNew = new ArrayList<>();
                    for (Map.Entry<String, Ad> entry : myAds.entrySet()) {
                        myAdNew.add(entry.getValue());
                    }
                    return myAdNew;
                }

                catch (MalformedURLException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
                catch (Exception e){e.printStackTrace();}
                finally {connection.disconnect();}

                //return null;
                return adListF;
            }

            @Override
            protected void onPostExecute(ArrayList<Ad> ads) {
                Collections.reverse(ads);   //new ad first
                adListF = ads;
                if (adListF.size() != 0) {
                    foundCustomAdapter = new AdAdapter(adListF, FoundActivity.this);
                    myListViewF.setAdapter(foundCustomAdapter);
                }
                else {  //if adList is empty (no ads or not internet) - alert dialog (pop up window)
                    //DialogFragment newFragmentInternet = new NoInternetFragment();
                    getSupportFragmentManager().beginTransaction().add(new NoInternetFragment(), getString(R.string.INTERNET)).commitAllowingStateLoss();
                }
            }
        }.execute();
    }
    }
     */

}
