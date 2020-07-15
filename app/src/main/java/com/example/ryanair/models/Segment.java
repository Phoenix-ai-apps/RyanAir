package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Segment implements Parcelable {
    private String segmentNr;
    private String origin;
    private String destination;
    private String flightNumber;
    private List<String> time;
    private List<String> timeUTC;
    private String duration;

    protected Segment(Parcel in) {
        segmentNr = in.readString();
        origin = in.readString();
        destination = in.readString();
        flightNumber = in.readString();
        time = in.createStringArrayList();
        timeUTC = in.createStringArrayList();
        duration = in.readString();
    }

    public static final Creator<Segment> CREATOR = new Creator<Segment>() {
        @Override
        public Segment createFromParcel(Parcel in) {
            return new Segment(in);
        }

        @Override
        public Segment[] newArray(int size) {
            return new Segment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(segmentNr);
        dest.writeString(origin);
        dest.writeString(destination);
        dest.writeString(flightNumber);
        dest.writeStringList(time);
        dest.writeStringList(timeUTC);
        dest.writeString(duration);
    }
}
