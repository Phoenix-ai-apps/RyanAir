package com.example.ryanair.ui.adapters;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryanair.R;
import com.example.ryanair.contants.AppConstants;
import com.example.ryanair.databinding.AdapterFlightDateoutBinding;
import com.example.ryanair.interfaces.FlightListListener;
import com.example.ryanair.models.Dates;
import com.example.ryanair.models.FlightDetails;
import com.example.ryanair.utils.ApplicationUtils;
import com.example.ryanair.utils.DateUtils;

import java.util.List;

public class FlightDateOutAdapter extends RecyclerView.Adapter<FlightDateOutAdapter.StationListViewHolder>
        implements AppConstants {

    private List<Dates> datesList;
    private FlightListListener listener;
    private String currency;
    private int lastSelectedPosition = -1;
    private int index = -1;

    public FlightDateOutAdapter(FlightListListener listener, List<Dates> datesList, String currency){
        this.listener = listener;
        this.datesList = datesList;
        this.currency = currency;
    }

    @NonNull
    @Override
    public StationListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        AdapterFlightDateoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.adapter_flight_dateout, viewGroup, false);
        return new StationListViewHolder(binding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(StationListViewHolder holder, int position) {
        Dates dates = datesList.get(position);
        if(!TextUtils.isEmpty(dates.getDateOut())){
            holder.binding.setDate(DateUtils.getFormattedDate(dates.getDateOut()));
        }
        FlightDetails flightDetails = null;
        if(dates.getFlights() != null && dates.getFlights().size() > 0){
            flightDetails = dates.getFlights().get(0);
            if(flightDetails != null && flightDetails.getRegularFare() != null &&
                    flightDetails.getRegularFare().getFares() != null
                    && flightDetails.getRegularFare().getFares().size() >0){
                holder.binding.setFare(ApplicationUtils.getRoundedFare(flightDetails.getRegularFare().getFares().get(0).getAmount()));
                holder.binding.setCurrency(currency);
                if (index == -1){
                    index = position;
                    listener.availableTicketPosition(index);
                }
            }
        }
        if(lastSelectedPosition == position){
            holder.binding.vw0.setVisibility(View.VISIBLE);
            listener.flightList(dates.getFlights());
        }else {
            holder.binding.vw0.setVisibility(View.INVISIBLE);
        }
    }

    class StationListViewHolder extends RecyclerView.ViewHolder {
        AdapterFlightDateoutBinding binding;
        public StationListViewHolder(AdapterFlightDateoutBinding itemsBinding) {
            super(itemsBinding.getRoot());
            binding = itemsBinding;
            binding.rlContainer.setOnClickListener((View)->{
                lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datesList != null && datesList.size() > 0) ? datesList.size() : 0;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

}
