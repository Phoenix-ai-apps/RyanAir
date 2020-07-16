package com.example.ryanair.interfaces;

import com.example.ryanair.models.FlightDetails;

import java.util.List;

public interface FlightListListener {
    void flightList(List<FlightDetails> detailsList);
    void availableTicketPosition(int position);
}
