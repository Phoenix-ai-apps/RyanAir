package com.example.ryanair.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private String date;
    private SimpleDateFormat formatter;
    private static final String TAG = DateUtils.class.getSimpleName();

    public DateUtils() { }

    public DateUtils(String date) {
        this.date = date;
    }

    public static String getFormattedDateWithoutT(String ourDate, boolean isTime) {
        SimpleDateFormat sdf;
        if(isTime){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        String dueDateAsNormal ="";
        try {
            value = sdf.parse(ourDate);
            SimpleDateFormat newFormatter = new SimpleDateFormat("MMM dd, yyyy");

            newFormatter.setTimeZone(TimeZone.getDefault());
            dueDateAsNormal = newFormatter.format(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dueDateAsNormal;

    }

    public static String getTimeFromDate(String ourDate) {
        if (ourDate!=null&&!TextUtils.isEmpty(ourDate)){
            SimpleDateFormat sdf = null;
            if(ourDate.contains("T")){
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            }else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            Date value = null;
            String dueDateAsNormal ="";
            try {
                value = sdf.parse(ourDate);
                SimpleDateFormat newFormatter = new SimpleDateFormat("kk:mm");
                newFormatter.setTimeZone(TimeZone.getDefault());
                dueDateAsNormal = newFormatter.format(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dueDateAsNormal;
        }
        return "";

    }

    public static String getFormattedDate(String ourDate) {
        if (ourDate!=null&&!TextUtils.isEmpty(ourDate)){
            SimpleDateFormat sdf = null;
            if(ourDate.contains("T")){
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            }else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            sdf.setTimeZone(TimeZone.getTimeZone("IST"));
            Date value = null;
            String dueDateAsNormal ="";
            try {
                value = sdf.parse(ourDate);
                SimpleDateFormat newFormatter = new SimpleDateFormat("MMM dd, yyyy");
                newFormatter.setTimeZone(TimeZone.getDefault());
                dueDateAsNormal = newFormatter.format(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dueDateAsNormal;
        }
        return "";


    }
}
