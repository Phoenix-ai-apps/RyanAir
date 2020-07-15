package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Station implements Parcelable {
     private String code;
     private String name;
     private String alternateName;
     private List<String> alias;
     private String countryCode;
     private String countryName;
     private String countryAlias;
     private String countryGroupCode;
     private String countryGroupName;
     private String timeZoneCode;
     private String latitude;
     private String longitude;
     private boolean mobileBoardingPass;
     private List<StationMarket> markets;
     private String notices;
     private String tripCardImageUrl;

    protected Station(Parcel in) {
        code = in.readString();
        name = in.readString();
        alternateName = in.readString();
        alias = in.createStringArrayList();
        countryCode = in.readString();
        countryName = in.readString();
        countryAlias = in.readString();
        countryGroupCode = in.readString();
        countryGroupName = in.readString();
        timeZoneCode = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        mobileBoardingPass = in.readByte() != 0;
        notices = in.readString();
        tripCardImageUrl = in.readString();
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryAlias() {
        return countryAlias;
    }

    public void setCountryAlias(String countryAlias) {
        this.countryAlias = countryAlias;
    }

    public String getCountryGroupCode() {
        return countryGroupCode;
    }

    public void setCountryGroupCode(String countryGroupCode) {
        this.countryGroupCode = countryGroupCode;
    }

    public String getCountryGroupName() {
        return countryGroupName;
    }

    public void setCountryGroupName(String countryGroupName) {
        this.countryGroupName = countryGroupName;
    }

    public String getTimeZoneCode() {
        return timeZoneCode;
    }

    public void setTimeZoneCode(String timeZoneCode) {
        this.timeZoneCode = timeZoneCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isMobileBoardingPass() {
        return mobileBoardingPass;
    }

    public void setMobileBoardingPass(boolean mobileBoardingPass) {
        this.mobileBoardingPass = mobileBoardingPass;
    }

    public List<StationMarket> getMarkets() {
        return markets;
    }

    public void setMarkets(List<StationMarket> markets) {
        this.markets = markets;
    }

    public String getNotices() {
        return notices;
    }

    public void setNotices(String notices) {
        this.notices = notices;
    }

    public String getTripCardImageUrl() {
        return tripCardImageUrl;
    }

    public void setTripCardImageUrl(String tripCardImageUrl) {
        this.tripCardImageUrl = tripCardImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(alternateName);
        dest.writeStringList(alias);
        dest.writeString(countryCode);
        dest.writeString(countryName);
        dest.writeString(countryAlias);
        dest.writeString(countryGroupCode);
        dest.writeString(countryGroupName);
        dest.writeString(timeZoneCode);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeByte((byte) (mobileBoardingPass ? 1 : 0));
        dest.writeString(notices);
        dest.writeString(tripCardImageUrl);
    }
}
