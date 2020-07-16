package com.example.ryanair.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.ryanair.R;
import com.example.ryanair.databinding.ActivityFlightBookingBinding;
import com.example.ryanair.interfaces.StationListener;
import com.example.ryanair.interfaces.ToolbarListener;
import com.example.ryanair.models.Station;
import com.example.ryanair.ui.adapters.StationAdapter;
import com.example.ryanair.ui.fragments.flightBookingActivity.PassengerFragment;
import com.example.ryanair.ui.fragments.flightBookingActivity.StationSelectionFragment;

public class FlightBookingActivity extends BaseActivity implements StationListener, ToolbarListener, View.OnClickListener {
    private static final String TAG = FlightBookingActivity.class.getSimpleName();
    private ActivityFlightBookingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight_booking);
        initResources();
    }

    @Override
    protected void initResources() {
        binding.incToolbar.ivBack.setOnClickListener(this);
        boolean isOrigin = false;
        if(getIntent().hasExtra(IS_ORIGIN)){
            isOrigin = getIntent().getBooleanExtra(IS_ORIGIN, false);
        }
        if(getIntent().hasExtra(FRAGMENT_NAME)){
            Fragment fragment = new Fragment();
            fragment.setArguments(getIntent().getExtras());
            if(getIntent().getStringExtra(FRAGMENT_NAME).equalsIgnoreCase(FRAGMENT_STATION_SELECTION)){
                fragment = new StationSelectionFragment();
                fragment.setArguments(getIntent().getExtras());
                if (isOrigin){
                    binding.incToolbar.tvMainText.setText(getString(R.string.select_origin));
                }else{
                    binding.incToolbar.tvMainText.setText(getString(R.string.select_destination));
                }
                replaceFragment(fragment, fragment.getClass().getSimpleName(), false);
            }else if(getIntent().getStringExtra(FRAGMENT_NAME).equalsIgnoreCase(FRAGMENT_PASSENGER_SELECTION)){
                fragment = new PassengerFragment();
                fragment.setArguments(getIntent().getExtras());
                replaceFragment(fragment, fragment.getClass().getSimpleName(), false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v == binding.incToolbar.ivBack){
            onBackPressed();
        }
    }

    @Override
    public void selectedStation(Station station) {
        Intent intent = new Intent();
        intent.putExtra(STATION, station);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void setToolbar(String headerText, String fromFragment) {
        binding.incToolbar.tvMainText.setVisibility(View.VISIBLE);
        binding.incToolbar.ivBack.setVisibility(View.VISIBLE);
        binding.incToolbar.ivLogo.setVisibility(View.GONE);
         if(getIntent().getStringExtra(FRAGMENT_NAME).equalsIgnoreCase(FRAGMENT_PASSENGER_SELECTION)){
            binding.incToolbar.tvMainText.setText(headerText);
        }
    }
}