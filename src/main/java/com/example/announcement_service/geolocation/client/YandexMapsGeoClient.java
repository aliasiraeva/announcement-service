package com.example.announcement_service.geolocation.client;

import com.example.announcement_service.geolocation.model.YandexGeolocationResponse;
import com.example.announcement_service.geolocation.model.GeoLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
public class YandexMapsGeoClient implements GeoClient {

    private static final String API_KEY = "05925e70-6c55-488d-b151-c4ba9883b6d2";
    private static final String FORMAT = "json";
    private final RestClient restClient;

    @Override
    public GeoLocation getLocation(String address) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://geocode-maps.yandex.ru/1.x/")
                .queryParam("apikey", API_KEY)
                .queryParam("geocode", address)
                .queryParam("format", FORMAT)
                .build().toUri();
        YandexGeolocationResponse response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(YandexGeolocationResponse.class);
        String location = response.getResponse().getGeoObjectCollection().getFeatureMember().get(0).getGeoObject().getPoint().getPos();
        String[] locations = location.split(" ");
        return GeoLocation.builder()
                .lng(Double.parseDouble(locations[0]))
                .lat(Double.parseDouble(locations[1]))
                .build();
    }

    @Override
    public String getAddress(GeoLocation location) {
        return "";
    }
}
