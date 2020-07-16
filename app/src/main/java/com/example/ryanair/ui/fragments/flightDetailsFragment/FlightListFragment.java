package com.example.ryanair.ui.fragments.flightDetailsFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanair.R;
import com.example.ryanair.RyanAirApp;
import com.example.ryanair.databinding.FragmentFlightListBinding;
import com.example.ryanair.interfaces.FlightDetailsListener;
import com.example.ryanair.interfaces.FlightListListener;
import com.example.ryanair.models.Dates;
import com.example.ryanair.models.Flight;
import com.example.ryanair.models.FlightDetails;
import com.example.ryanair.models.Trip;
import com.example.ryanair.ui.adapters.FlightAdapter;
import com.example.ryanair.ui.adapters.FlightDateOutAdapter;
import com.example.ryanair.ui.fragments.BaseFragment;
import com.example.ryanair.utils.ApplicationUtils;
import com.google.android.material.slider.Slider;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class FlightListFragment extends BaseFragment implements FlightListListener, FlightDetailsListener {

    private static final String TAG = FlightListFragment.class.getSimpleName();
    private FragmentFlightListBinding binding;
    private String currency ="", originName="", destinationName="";
    private FlightDateOutAdapter flightDateOutAdapter;
    private FlightAdapter flightAdapter;
    private LinearLayoutManager layoutManager;
    private List<FlightDetails> flightDetailsList= new ArrayList<>();

    public FlightListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false);
        initResources();
        return binding.getRoot();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_flight_list;
    }

    @Override
    protected void initResources() {
        layoutManager = new LinearLayoutManager(RyanAirApp.getInstance(), LinearLayoutManager.HORIZONTAL, false);
        Flight flight = getHelper().getFlight();
        Trip trip = null;
        if(flight != null && flight.getTrips() != null
                && flight.getTrips().size() > 0){
            if(!TextUtils.isEmpty(flight.getCurrency())){
                currency = flight.getCurrency();
                binding.setCurrency(currency);
            }
            trip = flight.getTrips().get(0);
            if(trip != null && trip.getDates() != null && trip.getDates().size() > 0){
                // All flight list for slider operation
                for(Dates dates : trip.getDates()){
                    if(dates != null && dates.getFlights() != null
                            && dates.getFlights().size() > 0){
                        flightDetailsList.addAll(dates.getFlights());
                    }
                }
                if(!TextUtils.isEmpty(trip.getOriginName())){
                    originName = trip.getOriginName();
                }
                if(!TextUtils.isEmpty(trip.getDestinationName())){
                    destinationName = trip.getDestinationName();
                }
                setUpDateOutAdapter(trip.getDates());
            }

            binding.slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(@NonNull Slider slider) { }

                @Override
                public void onStopTrackingTouch(@NonNull Slider slider) {
                   slider.getValue();
                    List<FlightDetails> filteredList = new ArrayList<>();
                    for (FlightDetails row : flightDetailsList) {
                        if(row != null && row.getRegularFare() != null
                                && row.getRegularFare().getFares() != null
                                && row.getRegularFare().getFares().size() >0
                                && row.getRegularFare().getFares().get(0).getAmount() !=0
                                && row.getRegularFare().getFares().get(0).getAmount() >= slider.getValue()) {
                            filteredList.add(row);
                        }
                    }
                   setUpFlightListAdapter(filteredList);
                }
            });
        }
    }

    private void setUpDateOutAdapter(List<Dates> datesList){
        flightDateOutAdapter = new FlightDateOutAdapter(this, datesList, currency);
        flightDateOutAdapter.setHasStableIds(true);
        binding.rvDateout.setNestedScrollingEnabled(false);
        binding.rvDateout.setHasFixedSize(true);
        binding.rvDateout.setLayoutManager(layoutManager);
        binding.rvDateout.setAdapter(flightDateOutAdapter);
    }

    private void setUpFlightListAdapter(List<FlightDetails> flightDetailsList){
        if(flightDetailsList != null && flightDetailsList.size() > 0){
            flightAdapter = new FlightAdapter(this, flightDetailsList, currency);
            flightAdapter.setHasStableIds(true);
            binding.rvFlight.setNestedScrollingEnabled(false);
            binding.rvFlight.setHasFixedSize(true);
            binding.rvFlight.setAdapter(flightAdapter);
            binding.tvNoFlight.setVisibility(View.GONE);
            binding.rvFlight.setVisibility(View.VISIBLE);
        }else{
            binding.tvNoFlight.setVisibility(View.VISIBLE);
            binding.rvFlight.setVisibility(View.GONE);
        }
    }

    @Override
    public void flightList(List<FlightDetails> detailsList) {
        setUpFlightListAdapter(detailsList);
    }

    @Override
    public void availableTicketPosition(int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(flightDateOutAdapter.getItemCount() >=position){
                    binding.rvDateout.findViewHolderForAdapterPosition(position).itemView.performClick();
                }
            }
        },100);
    }

    @Override
    public void flightDetails(FlightDetails details) {
        Bundle bundle = new Bundle();
        bundle.putString(ORIGIN_NAME, originName);
        bundle.putString(DESTINATION_NAME, destinationName);
        bundle.putParcelable(FLIGHT_DETAILS, details);
        Fragment fragment = new FlightDetailsFragment();
        fragment.setArguments(bundle);
        addFragment(fragment, fragment.getClass().getSimpleName(), true);
    }
}