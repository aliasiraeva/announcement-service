package com.example.announcement_service.service;

import com.example.announcement_service.entity.Announcement;
import com.example.announcement_service.entity.Image;
import com.example.announcement_service.geolocation.model.GeoLocation;
import com.example.announcement_service.geolocation.util.GeoUtils;
import com.example.announcement_service.model.DeepLink;
import com.example.announcement_service.model.ShortAnnouncement;
import com.example.announcement_service.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<Announcement> getAnnouncementsByDate() {
        return announcementRepository.findAll().stream()
                .sorted(Comparator.comparing(Announcement::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public Announcement getAnnouncementById(Integer id) {
        return announcementRepository.getReferenceById(id);
    }

    @Override
    public void addAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    @Override
    public void deleteAnnouncement(Integer id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public List<ShortAnnouncement> getShortAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        return mapToShortAnnouncements(announcements);
    }

    public List<ShortAnnouncement> mapToShortAnnouncements(List<Announcement> announcements) {
        return announcements.stream()
                .map(this::mapToShortAnnouncement)
                .toList();
    }
    public ShortAnnouncement mapToShortAnnouncement(Announcement announcement) {
        return ShortAnnouncement.builder()
                .image(getPreviewImage(announcement))
                .shortText(getShortText(announcement))
                .deepLink(new DeepLink())
                .build();
    }
    public String getShortText(Announcement announcement) {
        if (announcement.getText().length() > 30) {
            return announcement.getText().substring(0, 30);
        } else {
            return announcement.getText();
        }
    }
    public String getPreviewImage(Announcement announcement) {
        return announcement.getImages().stream()
                .filter(Image::isPreview)
                .findAny()
                .map(Image::getImageLink)
                .orElse(null);
    }

    @Override
    public List<Announcement> getNearestAnnouncements(double lat, double lng, int distance) {
        GeoLocation location = GeoLocation.builder().lat(lat).lng(lng).build();
        return announcementRepository.findAll().stream()
                .filter(announcement -> calcDistance(announcement, location) <= distance)
                .sorted(Comparator.comparing(announcement -> calcDistance(announcement, location)))
                .toList();
    }

    private int calcDistance(Announcement announcement, GeoLocation location) {
        GeoLocation announcementLocation = GeoLocation.builder()
                .lat(announcement.getLat())
                .lng(announcement.getLng())
                .build();
        return (int)(GeoUtils.calculateDistance(location, announcementLocation));
    }
}
