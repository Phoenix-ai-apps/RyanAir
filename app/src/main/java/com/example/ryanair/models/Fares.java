package com.example.ryanair.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Fares implements Parcelable {
      private String type;
      private float amount;
      private int count;
      private boolean hasDiscount;
      private float publishedFare;
      private int discountInPercent;
      private boolean hasPromoDiscount;
      private float discountAmount;

    protected Fares(Parcel in) {
        type = in.readString();
        amount = in.readFloat();
        count = in.readInt();
        hasDiscount = in.readByte() != 0;
        publishedFare = in.readFloat();
        discountInPercent = in.readInt();
        hasPromoDiscount = in.readByte() != 0;
        discountAmount = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeFloat(amount);
        dest.writeInt(count);
        dest.writeByte((byte) (hasDiscount ? 1 : 0));
        dest.writeFloat(publishedFare);
        dest.writeInt(discountInPercent);
        dest.writeByte((byte) (hasPromoDiscount ? 1 : 0));
        dest.writeFloat(discountAmount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Fares> CREATOR = new Creator<Fares>() {
        @Override
        public Fares createFromParcel(Parcel in) {
            return new Fares(in);
        }

        @Override
        public Fares[] newArray(int size) {
            return new Fares[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public float getPublishedFare() {
        return publishedFare;
    }

    public void setPublishedFare(float publishedFare) {
        this.publishedFare = publishedFare;
    }

    public int getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(int discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public boolean isHasPromoDiscount() {
        return hasPromoDiscount;
    }

    public void setHasPromoDiscount(boolean hasPromoDiscount) {
        this.hasPromoDiscount = hasPromoDiscount;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }
}
