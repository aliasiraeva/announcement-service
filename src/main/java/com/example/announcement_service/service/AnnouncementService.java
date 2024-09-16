package com.example.announcement_service.service;

import com.example.announcement_service.entity.Announcement;
import com.example.announcement_service.model.ShortAnnouncement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> getAnnouncementsByDate();
    Announcement getAnnouncementById(Integer id);
    void addAnnouncement(Announcement announcement);
    void deleteAnnouncement(Integer id);
    List<ShortAnnouncement> getShortAnnouncements();
    List<Announcement> getNearestAnnouncements(double lat, double lng, int distance);
}
