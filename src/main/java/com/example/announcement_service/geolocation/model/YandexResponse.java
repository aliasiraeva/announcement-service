package com.example.announcement_service.geolocation.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class YandexResponse {
    private GeoResponse response;

    @Data
    public static class GeoResponse {
        @JsonAlias("GeoObjectCollection")
        private GeoObjectCollection geoObjectCollection;
    }

    @Data
    public static class GeoObjectCollection {
        private List<FeatureMember> featureMember;
    }

    @Data
    public static class FeatureMember {
        @JsonAlias("GeoObject")
        private GeoObject geoObject;
    }

    @Data
    public static class GeoObject {
        @JsonAlias("Point")
        private Point point;
        private MetaDataProperty metaDataProperty;
    }

    @Data
    public static class Point {
        private String pos;
    }

    @Data
    public static class MetaDataProperty {
        @JsonAlias("GeocoderMetaData")
        private GeocoderMetaData geocoderMetaData;
    }

    @Data
    public static class GeocoderMetaData {
        @JsonAlias("Address")
        private Address address;
    }

    @Data
    public static class Address {
        private String formatted;
    }
}
