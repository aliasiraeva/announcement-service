package com.example.announcement_service.geolocation.model;

import lombok.Data;

import java.util.List;

@Data
public class YandexGeolocationResponse {
    private GeoResponse response;

    @Data
    public static class GeoResponse {
        private GeoObjectCollection geoObjectCollection;
    }

    @Data
    public static class GeoObjectCollection {
        private List<FeatureMember> featureMember;
    }

    @Data
    public static class FeatureMember {
        private GeoObject geoObject;
    }

    @Data
    public static class GeoObject {
        private String name;
        private String description;
        private Point point;
    }

    @Data
    public static class Point {
        private String pos;
    }
}
