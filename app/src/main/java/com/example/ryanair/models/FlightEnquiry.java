package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightEnquiry implements Parcelable {
    private String origin;
    private String originCode;
    private String destination;
    private String destinationCode;
    private String dateOut;
    private int    adult;
    private int    teen;
    private int    children;
    private int    infants;

    public FlightEnquiry(){}

    protected FlightEnquiry(Parcel in) {
        origin = in.readString();
        originCode = in.readString();
        destination = in.readString();
        destinationCode = in.readString();
        dateOut = in.readString();
        adult = in.readInt();
        teen = in.readInt();
        children = in.readInt();
        infants = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(origin);
        dest.writeString(originCode);
        dest.writeString(destination);
        dest.writeString(destinationCode);
        dest.writeString(dateOut);
        dest.writeInt(adult);
        dest.writeInt(teen);
        dest.writeInt(children);
        dest.writeInt(infants);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlightEnquiry> CREATOR = new Creator<FlightEnquiry>() {
        @Override
        public FlightEnquiry createFromParcel(Parcel in) {
            return new FlightEnquiry(in);
        }

        @Override
        public FlightEnquiry[] newArray(int size) {
            return new FlightEnquiry[size];
        }
    };

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getTeen() {
        return teen;
    }

    public void setTeen(int teen) {
        this.teen = teen;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }
}
