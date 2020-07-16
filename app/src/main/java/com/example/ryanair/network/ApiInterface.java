package com.example.ryanair.network;

import com.example.ryanair.models.Flight;
import com.example.ryanair.models.StationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/static/stations.json")
    Call<StationResponse> getAllStations();

    @GET("/api/booking/v4/en-gb/Availability")
    Call<Flight> getFlights(@Query("dateout") String dateOut,
                            @Query("roundtrip") boolean roundTrip,
                            @Query("origin") String origin,
                            @Query("destination") String destination,
                            @Query("flexdaysout") int flexdaysout,
                            @Query("flexdaysin") int flexdaysin,
                            @Query("flexdaysbeforeout") int flexdaysbeforeout,
                            @Query("flexdaysbeforein") int flexdaysbeforein,
                            @Query("adt") int adt,
                            @Query("chd") int chd,
                            @Query("teen") int teen,
                            @Query("inf") int inf,
                            @Query("ToUs") String toUs,
                            @Query("Disc") int disc);

}
