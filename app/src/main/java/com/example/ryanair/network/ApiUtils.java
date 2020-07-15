package com.example.ryanair.network;


public class ApiUtils {

    public static final int SECONDS_60   = 60;
    public static final String BASE_URL  = "https://tripstest.ryanair.com";

    public ApiUtils() {}

    public static ApiInterface getApiService(){
        return APIClient.getApiClient(BASE_URL, SECONDS_60).create(ApiInterface.class);
    }
}
