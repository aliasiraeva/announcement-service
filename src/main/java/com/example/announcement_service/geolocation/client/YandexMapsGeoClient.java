package com.example.announcement_service.geolocation.client;

import com.example.announcement_service.geolocation.model.YandexResponse;
import com.example.announcement_service.geolocation.model.GeoLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
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
        try {
            YandexResponse response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(YandexResponse.class);
            log.info("Получен ответ yandex maps api: {}", response);
            String location = response.getResponse().getGeoObjectCollection().getFeatureMember().get(0).getGeoObject().getPoint().getPos();
            String[] locations = location.split(" ");
            return GeoLocation.builder()
                    .lng(Double.parseDouble(locations[0]))
                    .lat(Double.parseDouble(locations[1]))
                    .build();
        } catch (Exception exception) {
            log.error("Ошибка при получении ответа yandex maps api: {}", exception.getMessage(), exception);
            return null;
        }
    }

    @Override
    public String getAddress(GeoLocation location) {
        String geocode = String.join(",", String.valueOf(location.getLng()), String.valueOf(location.getLat()));
        URI uri = UriComponentsBuilder.fromHttpUrl("https://geocode-maps.yandex.ru/1.x/")
                .queryParam("apikey", API_KEY)
                .queryParam("geocode", geocode)
                .queryParam("format", FORMAT)
                .build().toUri();
        log.info("Отправлен запрос к yandex maps api {}", uri.getPath());
        try {
            YandexResponse response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(YandexResponse.class);
            log.info("Получен ответ yandex maps api {}", response);
            return response.getResponse().getGeoObjectCollection().getFeatureMember().get(0).getGeoObject().getMetaDataProperty().getGeocoderMetaData().getAddress().getFormatted();
        } catch (Exception exception) {
            log.error("Ошибка при получении ответа yandex maps api: {}", exception.getMessage(), exception);
            return null;
        }
    }
}
