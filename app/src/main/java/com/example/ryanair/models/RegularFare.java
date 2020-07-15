package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RegularFare implements Parcelable {
    private String fareKey;
    private String fareClass;
    private List<Fares> fares;

    protected RegularFare(Parcel in) {
        fareKey = in.readString();
        fareClass = in.readString();
        fares = in.createTypedArrayList(Fares.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fareKey);
        dest.writeString(fareClass);
        dest.writeTypedList(fares);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegularFare> CREATOR = new Creator<RegularFare>() {
        @Override
        public RegularFare createFromParcel(Parcel in) {
            return new RegularFare(in);
        }

        @Override
        public RegularFare[] newArray(int size) {
            return new RegularFare[size];
        }
    };

    public String getFareKey() {
        return fareKey;
    }

    public void setFareKey(String fareKey) {
        this.fareKey = fareKey;
    }

    public String getFareClass() {
        return fareClass;
    }

    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    public List<Fares> getFares() {
        return fares;
    }

    public void setFares(List<Fares> fares) {
        this.fares = fares;
    }
}
