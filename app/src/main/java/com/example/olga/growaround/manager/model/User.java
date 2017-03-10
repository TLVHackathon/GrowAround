package com.example.olga.growaround.manager.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olga on 3/4/17.
 */

public class User implements Parcelable {

    @SerializedName("lastname")
    String lastName;

    @SerializedName("email")
    String emailAddress;

    @SerializedName("phone")
    int phoneNumber;

    @SerializedName("card")
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastName);
        dest.writeString(emailAddress);
        dest.writeInt(phoneNumber);
        dest.writeParcelable(card, flags);
    }
}
