package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Dates implements Parcelable {
    private String dateOut;
    private List<FlightDetails> flights;

    protected Dates(Parcel in) {
        dateOut = in.readString();
        flights = in.createTypedArrayList(FlightDetails.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateOut);
        dest.writeTypedList(flights);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Dates> CREATOR = new Creator<Dates>() {
        @Override
        public Dates createFromParcel(Parcel in) {
            return new Dates(in);
        }

        @Override
        public Dates[] newArray(int size) {
            return new Dates[size];
        }
    };

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public List<FlightDetails> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDetails> flights) {
        this.flights = flights;
    }
}
