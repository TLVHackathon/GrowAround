package com.example.olga.growaround.manager.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by olga on 3/4/17.
 */

public class Card implements Parcelable {

    String userName;
    int [] itemsSearch;
    int [] itemsOffer;
    int [] itemsGive;
    String userDetails;
    String location;


    public Card() {}

    protected Card(Parcel in) {
        userName = in.readString();
        itemsSearch = in.createIntArray();
        itemsOffer = in.createIntArray();
        itemsGive = in.createIntArray();
        userDetails = in.readString();
        location = in.readString();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeIntArray(itemsSearch);
        dest.writeIntArray(itemsOffer);
        dest.writeIntArray(itemsGive);
        dest.writeString(userDetails);
        dest.writeString(location);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int[] getItemsSearch() {
        return itemsSearch;
    }

    public void setItemsSearch(int[] itemsSearch) {
        this.itemsSearch = itemsSearch;
    }

    public int[] getItemsOffer() {
        return itemsOffer;
    }

    public void setItemsOffer(int[] itemsOffer) {
        this.itemsOffer = itemsOffer;
    }

    public int[] getItemsGive() {
        return itemsGive;
    }

    public void setItemsGive(int[] itemsGive) {
        this.itemsGive = itemsGive;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}