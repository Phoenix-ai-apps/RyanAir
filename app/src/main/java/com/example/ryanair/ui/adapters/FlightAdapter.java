package com.example.ryanair.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryanair.R;
import com.example.ryanair.contants.AppConstants;
import com.example.ryanair.databinding.AdapterFlightBinding;
import com.example.ryanair.interfaces.FlightDetailsListener;
import com.example.ryanair.models.FlightDetails;
import com.example.ryanair.utils.ApplicationUtils;
import com.example.ryanair.utils.DateUtils;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.StationListViewHolder>
        implements AppConstants {

    private List<FlightDetails> flightDetailsList;
    private FlightDetailsListener listener;
    private String currency;

    public FlightAdapter(FlightDetailsListener listener, List<FlightDetails> flightDetailsList, String currency){
        this.listener = listener;
        this.flightDetailsList = flightDetailsList;
        this.currency = currency;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StationListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        AdapterFlightBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.adapter_flight, viewGroup, false);
        return new StationListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StationListViewHolder holder, int position) {
        FlightDetails flightDetails = flightDetailsList.get(position);
        if(flightDetails != null && flightDetails.getSegments() != null
                && flightDetails.getSegments().size() >0){
            holder.binding.setSegment(flightDetails.getSegments().get(0));
            holder.binding.setCurrency(currency);
            if(flightDetails.getRegularFare() != null &&
                flightDetails.getRegularFare().getFares() != null
                && flightDetails.getRegularFare().getFares().size() >0){
                holder.binding.setFare(ApplicationUtils.getRoundedFare(flightDetails.getRegularFare().getFares().get(0).getAmount()));
            }
            if(flightDetails.getTime() != null && flightDetails.getTime().size() >1){
                holder.binding.setDepTime(DateUtils.getTimeFromDate(flightDetails.getTime().get(0)));
                holder.binding.setArrTime(DateUtils.getTimeFromDate(flightDetails.getTime().get(1)));
            }
            holder.binding.rlContainer.setOnClickListener((View)->{
               listener.flightDetails(flightDetails);
            });
        }
    }

    class StationListViewHolder extends RecyclerView.ViewHolder {
        AdapterFlightBinding binding;
        public StationListViewHolder(AdapterFlightBinding itemsBinding) {
            super(itemsBinding.getRoot());
            binding = itemsBinding;
        }
    }

    @Override
    public int getItemCount() {
        return (flightDetailsList != null && flightDetailsList.size() > 0) ? flightDetailsList.size() : 0;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

}
