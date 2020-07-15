package com.example.ryanair.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private String date;
    private SimpleDateFormat formatter;
    private static final String TAG = DateUtils.class.getSimpleName();

    public DateUtils() {
    }

    public DateUtils(String date) {
        this.date = date;
    }

    public String convertStringToDate(String dateStr){
        Date eventDate = null;
        try {
             eventDate=formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return eventDate.toString();
    }

    public static String getFormatedDate(String ourDate) {
        if (ourDate!=null&&!TextUtils.isEmpty(ourDate)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = null;
            String dueDateAsNormal ="";
            try {
                value = sdf.parse(ourDate);
                SimpleDateFormat newFormatter = new SimpleDateFormat("MMM dd, hh:mm a");

                newFormatter.setTimeZone(TimeZone.getDefault());
                dueDateAsNormal = newFormatter.format(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dueDateAsNormal;
        }
        return "";
    }

    public static String getFormatedDateWithoutT(String ourDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    public static String getFormatedDateWithT(String ourDate) {
        if (ourDate!=null&&!TextUtils.isEmpty(ourDate)){
            SimpleDateFormat sdf = null;
            if(ourDate.contains("T")){
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            }else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = null;
            String dueDateAsNormal ="";
            try {
                value = sdf.parse(ourDate);
                SimpleDateFormat newFormatter = new SimpleDateFormat("MMM dd, yyyy  hh:mm a");

                newFormatter.setTimeZone(TimeZone.getDefault());
                dueDateAsNormal = newFormatter.format(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dueDateAsNormal;
        }
        return "";

    }

    public static CharSequence getTimeAgoFormat(String dateFromServer){
        if (dateFromServer!=null&&!TextUtils.isEmpty(dateFromServer)){
            long time = 0;
            long now = System.currentTimeMillis();
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// server date and time coming in this format
            //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdff.setTimeZone(TimeZone.getTimeZone("IST"));
            try {
                //sdff.setTimeZone(TimeZone.getDefault());
                time = sdff.parse(dateFromServer).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            CharSequence ago = android.text.format.DateUtils.getRelativeTimeSpanString(time, now, android.text.format.DateUtils.MINUTE_IN_MILLIS);
            return ago;
        }
        return "";
    }

    /*public String getTimeInAgoFormat(){
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = format.parse(m.dateTime());

    String display = DateUtils.getRelativeTimeSpanString(
            date.getTime(),
            new Date(),
            DateUtils.MINUTE_IN_MILLIS);
    }*/

    public static String getFormatedDateForNotif(String ourDate) {
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
                SimpleDateFormat newFormatter = new SimpleDateFormat("hh:mm a");

                newFormatter.setTimeZone(TimeZone.getDefault());
                dueDateAsNormal = newFormatter.format(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dueDateAsNormal;
        }
        return "";


    }


    public static String getLongDate(String rawDate){

        if (rawDate!=null&&!TextUtils.isEmpty(rawDate)){
            SimpleDateFormat sdf = null;
            if(rawDate.contains("T")){
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            }else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = null;
            String dueDateAsNormal ="";
            long timeInMilliseconds = 0;
            try {
                value = sdf.parse(rawDate);
                timeInMilliseconds = value.getTime();
                ApplicationUtils.printLog(TAG, "timeInMilliseconds-> "+ String.valueOf(timeInMilliseconds));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return String.valueOf(timeInMilliseconds);
        }
        return "";
    }

    public String getDate(Date d)
    {
        DateFormat df2 = new SimpleDateFormat("MMM dd,yyyy hh:mm aaa");
        return df2.format(d);
    }
}
