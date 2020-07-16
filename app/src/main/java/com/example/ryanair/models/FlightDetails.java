package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightDetails implements Parcelable {
     private int    faresLeft;
     private String flightKey;
     private int    infantsLeft;
     private RegularFare regularFare;
     private String operatedBy;
     private List<Segment> segments;
     private String flightNumber;
     private List<String> time;
     private List<String> timeUTC;
     private String duration;

    protected FlightDetails(Parcel in) {
        faresLeft = in.readInt();
        flightKey = in.readString();
        infantsLeft = in.readInt();
        regularFare = in.readParcelable(RegularFare.class.getClassLoader());
        operatedBy = in.readString();
        segments = in.createTypedArrayList(Segment.CREATOR);
        flightNumber = in.readString();
        time = in.createStringArrayList();
        timeUTC = in.createStringArrayList();
        duration = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(faresLeft);
        dest.writeString(flightKey);
        dest.writeInt(infantsLeft);
        dest.writeParcelable(regularFare, flags);
        dest.writeString(operatedBy);
        dest.writeTypedList(segments);
        dest.writeString(flightNumber);
        dest.writeStringList(time);
        dest.writeStringList(timeUTC);
        dest.writeString(duration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlightDetails> CREATOR = new Creator<FlightDetails>() {
        @Override
        public FlightDetails createFromParcel(Parcel in) {
            return new FlightDetails(in);
        }

        @Override
        public FlightDetails[] newArray(int size) {
            return new FlightDetails[size];
        }
    };

    public int getFaresLeft() {
        return faresLeft;
    }

    public void setFaresLeft(int faresLeft) {
        this.faresLeft = faresLeft;
    }

    public String getFlightKey() {
        return flightKey;
    }

    public void setFlightKey(String flightKey) {
        this.flightKey = flightKey;
    }

    public int getInfantsLeft() {
        return infantsLeft;
    }

    public void setInfantsLeft(int infantsLeft) {
        this.infantsLeft = infantsLeft;
    }

    public RegularFare getRegularFare() {
        return regularFare;
    }

    public void setRegularFare(RegularFare regularFare) {
        this.regularFare = regularFare;
    }

    public String getOperatedBy() {
        return operatedBy;
    }

    public void setOperatedBy(String operatedBy) {
        this.operatedBy = operatedBy;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
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
}
