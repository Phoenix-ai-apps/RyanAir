package com.example.ryanair.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.example.ryanair.R;
import com.example.ryanair.RyanAirApp;
import com.example.ryanair.databinding.ActivityMainBinding;
import com.example.ryanair.interfaces.DataRefreshListener;
import com.example.ryanair.utils.ApplicationUtils;
import com.example.ryanair.utils.DialogUtils;
import com.example.ryanair.utils.NetworkUtils;

public class MainActivity extends BaseActivity implements DataRefreshListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private Dialog dialog;
    private DataRefreshListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initResources();
    }

    @Override
    protected void initResources() {
        listener = this::dataRefresh;
        if(NetworkUtils.isConnected(RyanAirApp.getInstance())){
            getAllStation(listener);
        }else {
            dialog = DialogUtils.showNoInternetDialog(MainActivity.this);
        }
    }

    @Override
    public void dataRefresh(boolean isRefreshed) {
        appExecutors.getExeMainThread().execute(()->{
            if(isRefreshed){
                ApplicationUtils.startActivityIntent(MainActivity.this, HomeActivity.class, null);
            }else {
                ApplicationUtils.showSnackBar(this, getString(R.string.something_went_wrong));
            }
            finish();
        });
    }
}