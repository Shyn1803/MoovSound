package com.example.computer.moovsound.Service;

public class APIService {
    private static String base_url="https://hangitntu.000webhostapp.com/Server/";

    public static IDataService getService() {
        return APIRetrofixClient.getClient(base_url).create ( IDataService.class );
    }
}
