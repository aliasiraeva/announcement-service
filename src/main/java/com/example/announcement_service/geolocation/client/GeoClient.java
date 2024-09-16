package com.example.announcement_service.geolocation.client;

import com.example.announcement_service.geolocation.model.GeoLocation;

public interface GeoClient {
    GeoLocation getLocation(String address);
    String getAddress(GeoLocation location);
}
