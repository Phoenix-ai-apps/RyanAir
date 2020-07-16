package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Flight implements Parcelable {
    private String termsOfUse;
    private String currency;
    private int    currPrecision;
    private String routeGroup;
    private String tripType;
    private String upgradeType;
    private String serverTimeUTC;
    private List<Trip> trips;

    protected Flight(Parcel in) {
        termsOfUse = in.readString();
        currency = in.readString();
        currPrecision = in.readInt();
        routeGroup = in.readString();
        tripType = in.readString();
        upgradeType = in.readString();
        serverTimeUTC = in.readString();
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCurrPrecision() {
        return currPrecision;
    }

    public void setCurrPrecision(int currPrecision) {
        this.currPrecision = currPrecision;
    }

    public String getRouteGroup() {
        return routeGroup;
    }

    public void setRouteGroup(String routeGroup) {
        this.routeGroup = routeGroup;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(String upgradeType) {
        this.upgradeType = upgradeType;
    }

    public String getServerTimeUTC() {
        return serverTimeUTC;
    }

    public void setServerTimeUTC(String serverTimeUTC) {
        this.serverTimeUTC = serverTimeUTC;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(termsOfUse);
        dest.writeString(currency);
        dest.writeInt(currPrecision);
        dest.writeString(routeGroup);
        dest.writeString(tripType);
        dest.writeString(upgradeType);
        dest.writeString(serverTimeUTC);
    }
}
