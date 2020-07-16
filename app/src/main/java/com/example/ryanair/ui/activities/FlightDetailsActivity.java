package com.example.ryanair.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.ryanair.R;
import com.example.ryanair.databinding.ActivityFlightDetailsBinding;
import com.example.ryanair.ui.fragments.flightDetailsFragment.FlightListFragment;

public class FlightDetailsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = FlightDetailsActivity.class.getSimpleName();
    private ActivityFlightDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight_details);
        initResources();
    }

    @Override
    protected void initResources() {
        binding.incToolbar.ivBack.setOnClickListener(this::onClick);
        if(getHelper().getFlight() != null
                && getHelper().getFlight().getTrips() != null
                && getHelper().getFlight().getTrips().size() > 0){
            binding.incToolbar.setTrip(getHelper().getFlight().getTrips().get(0));
            replaceFragment(new FlightListFragment(), FlightListFragment.class.getSimpleName(), false);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == binding.incToolbar.ivBack){
            onBackPressed();
        }
    }
}