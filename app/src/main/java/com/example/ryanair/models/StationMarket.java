package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

public class StationMarket implements Parcelable {
    private String code;
    private String group;

    protected StationMarket(Parcel in) {
        code = in.readString();
        group = in.readString();
    }

    public static final Creator<StationMarket> CREATOR = new Creator<StationMarket>() {
        @Override
        public StationMarket createFromParcel(Parcel in) {
            return new StationMarket(in);
        }

        @Override
        public StationMarket[] newArray(int size) {
            return new StationMarket[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(group);
    }
}
