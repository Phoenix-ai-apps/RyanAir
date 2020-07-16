package com.example.ryanair.ui.fragments.flightBookingActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanair.R;
import com.example.ryanair.databinding.FragmentStationSelectionBinding;
import com.example.ryanair.interfaces.StationListener;
import com.example.ryanair.interfaces.ToolbarListener;
import com.example.ryanair.models.Station;
import com.example.ryanair.ui.adapters.StationAdapter;
import com.example.ryanair.ui.fragments.BaseFragment;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class StationSelectionFragment extends BaseFragment implements StationListener{

    private static final String TAG = StationSelectionFragment.class.getSimpleName();
    private FragmentStationSelectionBinding binding;
    private StationAdapter stationAdapter;

    public StationSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false);
        initResources();
        return binding.getRoot();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_station_selection;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initResources() {
        if(getActivity() != null && isAdded()){
            ToolbarListener toolbarListener =(ToolbarListener) getActivity();
            toolbarListener.setToolbar("", FRAGMENT_STATION_SELECTION);
        }
        String originCode ="", destinationCode="";
        if(getArguments() != null){
            if(getArguments().containsKey(ORIGIN_CO)){
                originCode = getArguments().getString(ORIGIN_CO);
            }
            if(getArguments().containsKey(DESTINATION_CO)){
                destinationCode = getArguments().getString(DESTINATION_CO);
            }
        }
        if (getHelper().getStations() != null && getHelper().getStations().size() > 0){
            List<Station> stationList = getHelper().getStations();
            stationAdapter = new StationAdapter(this::selectedStation, stationList);
            stationAdapter.setHasStableIds(true);
            binding.rvStation.setNestedScrollingEnabled(false);
            binding.rvStation.setHasFixedSize(true);
            binding.rvStation.setAdapter(stationAdapter);
        }
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               if(!TextUtils.isEmpty(s.toString())){
                   stationAdapter.getFilter().filter(s.toString().trim());
               }
            }
        });
    }

    @Override
    public void selectedStation(Station station) {
        if(getActivity() != null && isAdded()){
            Intent intent = new Intent();
            intent.putExtra(STATION, station);
            getActivity().setResult(RESULT_OK, intent);
            getActivity().finish();
        }
    }
}