package com.example.ryanair.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryanair.R;
import com.example.ryanair.contants.AppConstants;
import com.example.ryanair.databinding.AdapterStationBinding;
import com.example.ryanair.interfaces.StationListener;
import com.example.ryanair.models.Station;

import java.util.ArrayList;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationListViewHolder>
        implements AppConstants, Filterable {

    private List<Station> stationList;
    private List<Station> stationFilteredList;
    private StationListener stationListener;

    public StationAdapter(StationListener listener, List<Station> stationList){
        stationListener = listener;
        this.stationList = stationList;
        this.stationFilteredList = stationList;
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
        Station station = stationFilteredList.get(position);
        holder.binding.setStation(station);
        holder.binding.rlContainer.setOnClickListener((View)->{
            stationListener.selectedStation(station);
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    stationFilteredList = stationList;
                } else {
                    List<Station> filteredList = new ArrayList<>();
                    for (Station row : stationList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())
                                || row.getCode().toLowerCase().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    stationFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = stationFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                stationFilteredList = (ArrayList<Station>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
        return (stationFilteredList != null && stationFilteredList.size() > 0) ? stationFilteredList.size() : 0;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

}
