package com.example.ryanair.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.ryanair.R;
import com.example.ryanair.databinding.ActivityHomeBinding;
import com.example.ryanair.databinding.ActivityStationSearchBinding;
import com.example.ryanair.interfaces.StationListener;
import com.example.ryanair.interfaces.ToolbarListener;
import com.example.ryanair.models.Station;
import com.example.ryanair.ui.adapters.StationAdapter;
import com.example.ryanair.ui.fragments.homeFragment.HomeFragment;

public class StationSearchActivity extends BaseActivity implements StationListener, View.OnClickListener {
    private static final String TAG = StationSearchActivity.class.getSimpleName();
    private ActivityStationSearchBinding binding;
    private StationAdapter stationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_station_search);
        boolean isOrigin = false;
        if(getIntent().hasExtra(IS_ORIGIN)){
            isOrigin = getIntent().getBooleanExtra(IS_ORIGIN, false);
            setUpToolbar(isOrigin);
        }
         initResources();
    }

    @Override
    protected void initResources() {
        binding.incToolbar.ivBack.setOnClickListener(this);
        if (getHelper().getStations() != null && getHelper().getStations().size() > 0){
            stationAdapter = new StationAdapter(this::selectedStation, getHelper().getStations());
            stationAdapter.setHasStableIds(true);
            binding.rvStation.setNestedScrollingEnabled(false);
            binding.rvStation.setHasFixedSize(true);
            binding.rvStation.setAdapter(stationAdapter);
        }
    }

    private void setUpToolbar(boolean isOrigin) {
        binding.incToolbar.tvMainText.setVisibility(View.VISIBLE);
        binding.incToolbar.ivBack.setVisibility(View.VISIBLE);
        binding.incToolbar.ivLogo.setVisibility(View.GONE);
        if (isOrigin){
            binding.incToolbar.tvMainText.setText(getString(R.string.select_origin));
        }else
            binding.incToolbar.tvMainText.setText(getString(R.string.select_destination));
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
}