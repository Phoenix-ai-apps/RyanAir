package com.example.ryanair.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.ryanair.BuildConfig;
import com.example.ryanair.R;
import com.example.ryanair.RyanAirApp;
import com.example.ryanair.contants.AppConstants;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationUtils implements AppConstants {

    public static String toJson(Object object) {
        if (object != null) {
            Gson gson = new Gson();
            String json = gson.toJson(object);
            return json;
        } else {
            return "";
        }
    }

    public static void printLog(String strTag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(strTag, !TextUtils.isEmpty(message) ? message : "Message string is null");
        }
    }

    public static void startActivityIntent(Context context, Class aClass, Bundle bundle) {
        Intent intent = new Intent(context, aClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static void hideKeyboard(Activity activity, View view) {
        if (activity != null){
            InputMethodManager inputManager = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showSnackBar(Activity activity, String txt_message) {
        if(activity != null){
            View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar.make(view, txt_message, Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(RyanAirApp.getInstance().getResources().getColor(R.color.colorPrimaryDark));
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(RyanAirApp.getInstance().getResources().getColor(R.color.white));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            } else {
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            snackbar.show();
        }
    }

    public static String getFormattedString(long value){
        String conValue ="";
        if(value != 0){
            DecimalFormat df = new DecimalFormat("##,##,##0");
           // String s = String.valueOf(value);
            conValue = df.format(value);
        }else {
            conValue = "0";
        }

        return conValue;
    }

    public static String getPassengerText(int adult, int teen, int child, int infant){
        String val = "";
        if(adult > 1){
            val =  adult + " Adults";
        }else {
            val = adult + " Adult";
        }
        if(teen >= 1 || child >= 1 || infant >= 1){
            if(teen > 1){
                val = val +", "+teen+" Teens";
            }else if(teen == 1) {
                val = val +", "+teen+" Teen";
            }

            if(child > 1){
                val = val +", "+child+" Children";
            }else if(child == 1) {
                val = val +", "+child+" Child";
            }

            if(infant > 1){
                val = val +", "+infant+" Infants";
            }else if(infant == 1) {
                val = val +", "+infant+" Infant";
            }

        }
        return val;
    }

    public static String getRoundedFare(float fare){
        String val="";
        val = String.valueOf(Math.round(fare * 100.0) / 100.0);
        return val;
    }

}
