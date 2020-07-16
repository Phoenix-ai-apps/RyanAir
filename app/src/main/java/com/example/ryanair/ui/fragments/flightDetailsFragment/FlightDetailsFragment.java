package com.example.ryanair.ui.fragments.flightDetailsFragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanair.R;
import com.example.ryanair.databinding.FragmentFlightDetailsBinding;
import com.example.ryanair.models.FlightDetails;
import com.example.ryanair.ui.fragments.BaseFragment;

public class FlightDetailsFragment extends BaseFragment {

    private static final String TAG = FlightDetailsFragment.class.getSimpleName();
    private FragmentFlightDetailsBinding binding;

    public FlightDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
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
        return R.layout.fragment_flight_details;
    }

    @Override
    protected void initResources() {
      if(getArguments() != null){
          if(getArguments().containsKey(ORIGIN_NAME)){
            binding.setOrigin(getArguments().getString(ORIGIN_NAME));
          }if(getArguments().containsKey(DESTINATION_NAME)){
            binding.setDestination(getArguments().getString(DESTINATION_NAME));
          }if(getArguments().containsKey(FLIGHT_DETAILS)){
              FlightDetails flightDetails = getArguments().getParcelable(FLIGHT_DETAILS);
              if(flightDetails != null){
                  binding.setInfantLeft(String.valueOf(flightDetails.getInfantsLeft()));
                  if(flightDetails.getRegularFare() != null){
                      binding.setFareClass(flightDetails.getRegularFare().getFareClass());
                      if(flightDetails.getRegularFare().getFares() != null
                              && flightDetails.getRegularFare().getFares().size() > 0){
                          binding.setDiscountPer(""+flightDetails.getRegularFare().getFares().get(0).getDiscountInPercent());
                      }
                  }
              }
          }
      }
    }
}