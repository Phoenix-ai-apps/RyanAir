package com.example.ryanair.helper;

import android.util.Log;

import com.example.ryanair.models.Flight;
import com.example.ryanair.models.Station;
import com.example.ryanair.utils.ApplicationUtils;

import java.util.List;

public class ApplicationHelper {

    private static final String TAG = ApplicationHelper.class.getSimpleName();
    private static ApplicationHelper _instance;
    private List<Station> stations;
    private Flight flight;

    public synchronized static ApplicationHelper getInstance() {
        if (_instance == null) {
            _instance = new ApplicationHelper();
            ApplicationUtils.printLog(TAG, "instance of ActivityHelper created.");
        }
        return _instance;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
