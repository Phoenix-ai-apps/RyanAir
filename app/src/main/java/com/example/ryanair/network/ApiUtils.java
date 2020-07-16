package com.example.ryanair.network;


public class ApiUtils {

    public static final int SECONDS_60   = 60;
    public static final String BASE_URL         = "https://tripstest.ryanair.com";
    public static final String BASE_URL_FLIGHT  = "https://www.ryanair.com";

    public ApiUtils() {}

    public static ApiInterface getApiService(boolean isFlightDetails){
        ApiInterface apiInterface;
        if(isFlightDetails){
            apiInterface = APIClient.getApiClient(BASE_URL_FLIGHT, SECONDS_60).create(ApiInterface.class);
        }else {
            apiInterface =  APIClient.getApiClient(BASE_URL, SECONDS_60).create(ApiInterface.class);
        }
        return apiInterface;
    }
}
