package com.example.ryanair.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryanair.R;
import com.example.ryanair.contants.AppConstants;
import com.example.ryanair.databinding.AdapterStationBinding;
import com.example.ryanair.interfaces.StationListener;
import com.example.ryanair.models.Station;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationListViewHolder>
        implements AppConstants {

    private List<Station> stationList;
    private StationListener stationListener;

    public StationAdapter(StationListener listener, List<Station> stationList){
        stationListener = listener;
        this.stationList = stationList;
    }

    @NonNull
    @Override
    public StationListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        AdapterStationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.adapter_station, viewGroup, false);
        return new StationListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StationListViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.binding.setStation(station);
        holder.binding.rlContainer.setOnClickListener((View)->{
            stationListener.selectedStation(station);
        });
    }

    class StationListViewHolder extends RecyclerView.ViewHolder {
        AdapterStationBinding binding;
        public StationListViewHolder(AdapterStationBinding itemsBinding) {
            super(itemsBinding.getRoot());
            binding = itemsBinding;
        }
    }

    @Override
    public int getItemCount() {
        return (stationList != null && stationList.size() > 0) ? stationList.size() : 0;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

}
