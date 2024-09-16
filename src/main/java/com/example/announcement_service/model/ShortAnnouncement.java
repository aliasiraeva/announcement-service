package com.example.announcement_service.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShortAnnouncement {
    private String image;
    private String shortText;
    private DeepLink deepLink;
}
