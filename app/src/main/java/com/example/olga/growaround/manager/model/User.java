package com.example.olga.growaround.manager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by olga on 3/4/17.
 */

public class User implements Parcelable {

    String lastName;
    String emailAddress;
    int phoneNumber;
    Card card;


    protected User(Parcel in) {
        lastName = in.readString();
        emailAddress = in.readString();
        phoneNumber = in.readInt();
        card = in.readParcelable(Card.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastName);
        dest.writeString(emailAddress);
        dest.writeInt(phoneNumber);
        dest.writeParcelable(card, flags);
    }
}
