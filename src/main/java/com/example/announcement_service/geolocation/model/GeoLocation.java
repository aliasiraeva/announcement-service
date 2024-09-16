package com.example.announcement_service.geolocation.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeoLocation {
    private double lat;
    private double lng;
}
