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

    public String getSegmentNr() {
        return segmentNr;
    }

    public void setSegmentNr(String segmentNr) {
        this.segmentNr = segmentNr;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getTimeUTC() {
        return timeUTC;
    }

    public void setTimeUTC(List<String> timeUTC) {
        this.timeUTC = timeUTC;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static Creator<Segment> getCREATOR() {
        return CREATOR;
    }

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
