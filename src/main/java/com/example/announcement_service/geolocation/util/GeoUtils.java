package com.example.announcement_service.geolocation.util;


import com.example.announcement_service.geolocation.model.GeoLocation;

public class GeoUtils {
    private static final int EARTH_RADIUS = 6371000;

    private static double toRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    /** Вычисляет расстояние между двумя точками на Земле в метрах */
    public static double calculateDistance(GeoLocation first, GeoLocation second) {
        double lat1Rad = toRadians(first.getLat());
        double lng1Rad = toRadians(first.getLng());
        double lat2Rad = toRadians(second.getLat());
        double lng2Rad = toRadians(second.getLng());

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLng = lng2Rad - lng1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}
