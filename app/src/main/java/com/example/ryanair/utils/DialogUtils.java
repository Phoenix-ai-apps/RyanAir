package com.example.ryanair.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.ryanair.R;
import com.example.ryanair.contants.AppConstants;
import com.example.ryanair.databinding.DialogAlertMessageBinding;
import com.example.ryanair.databinding.DialogNoInternetBinding;
import com.example.ryanair.databinding.DialogPleaseWaitBinding;
import com.example.ryanair.ui.activities.MainActivity;

import java.util.Map;

public class DialogUtils implements AppConstants {

    public static Dialog showLoadingDialog(Context context, String msg) {
        Dialog alert = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DialogPleaseWaitBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.dialog_please_wait, null, false);
        binding.tvMessage.setText(msg);
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alert.setContentView(binding.getRoot());
        alert.show();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        alert.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
        return alert;
    }

    public static Dialog showNoInternetDialog(AppCompatActivity activity) {
        Dialog alert = new Dialog(activity);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DialogNoInternetBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.dialog_no_internet, null, false);
        binding.tvDismiss.setOnClickListener((View)->{
            alert.dismiss();
            activity.finish();
        });
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alert.setContentView(binding.getRoot());
        alert.show();
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        alert.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
        return alert;
    }
}
