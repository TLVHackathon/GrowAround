package com.example.olga.growaround.manager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by olga on 3/4/17.
 */

public class Card implements Parcelable {

    String userName;

    String location;


    protected Card(Parcel in) {
        userName = in.readString();
        location = in.readString();
    }

    public Card() {}

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {return new Card(in);}
        @Override
        public Card[] newArray(int size) {return new Card[size];}
    };

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(location);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {this.location = location;}

}