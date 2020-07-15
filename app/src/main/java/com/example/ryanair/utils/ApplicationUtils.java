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

    public static boolean isValidName(String name){
        boolean isValid = false;
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        if(!TextUtils.isEmpty(name)){
            Matcher matcher = pattern.matcher(name);
            if(matcher.matches())
            {
                isValid = true;
            }
            else
            {
                isValid = false;
            }
        }else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPassword(String pwdd){
        boolean isValid = false;
       // Pattern pattern = Pattern.compile(new String ("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!.]).{6,16})"));
        // ^[a-zA-Z0-9]+$
        Pattern pattern = Pattern.compile(new String ("((?=.*\\d)(?=.*[a-zA-Z0-9]).{6,16})"));
        if(!TextUtils.isEmpty(pwdd)){
            Matcher matcher = pattern.matcher(pwdd);
            if(matcher.matches())
            {
                isValid = true;
            }
            else
            {
                isValid = false;
            }
        }else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isPasswordMatched(String pwdd, String cnfmPwdd){
        boolean isValid = false;
        if(!TextUtils.isEmpty(pwdd) && !TextUtils.isEmpty(cnfmPwdd)){
            if(pwdd.equalsIgnoreCase(cnfmPwdd)){
                isValid = true;
            }else {
                isValid = false;
            }
        }else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidMobileNo(String mobileNo){
        boolean isValid = false;
        if(!TextUtils.isEmpty(mobileNo) && mobileNo.length() == 10){
            char firstDigit = mobileNo.charAt(0);
            if(firstDigit == '5' || firstDigit == '6' || firstDigit == '7' || firstDigit == '8' || firstDigit == '9'){
                isValid = true;
            }else {
                isValid = false;
            }
        }else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValid4DigitOTP(String otpVal1,String otpVal2,String otpVal3,String otpVal4){
        boolean isValid = false;
        if(!TextUtils.isEmpty(otpVal1)){
            if(!TextUtils.isEmpty(otpVal2)){
                if(!TextUtils.isEmpty(otpVal3)){
                    if(!TextUtils.isEmpty(otpVal4)){
                        isValid = true;
                    }else {
                       isValid = false;
                    }
                }else {
                    isValid = false;
                }
            }else {
                isValid = false;
            }
        }else {
            isValid = false;
        }

        return isValid;
    }

    public static boolean isValidPincode(String pincode){
        boolean isValid = false;
        if(!TextUtils.isEmpty(pincode) && pincode.length() == 6){
            isValid = true;
        }else {
            isValid = false;
        }
        return isValid;
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

}
