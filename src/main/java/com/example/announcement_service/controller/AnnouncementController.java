package com.example.announcement_service.controller;

import com.example.announcement_service.entity.Announcement;
import com.example.announcement_service.model.ShortAnnouncement;
import com.example.announcement_service.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/list")
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAnnouncementsByDate();
    }

    @GetMapping("/{id}")
    public Announcement getAnnouncementById(@PathVariable Integer id) {
        return announcementService.getAnnouncementById(id);
    }

    @PostMapping
    public void postAnnouncement(Announcement announcement) {
        announcementService.addAnnouncement(announcement);
    }

    @DeleteMapping
    public void deleteAnnouncement(Integer id) {
        announcementService.deleteAnnouncement(id);
    }

    @GetMapping("/short")
    public List<ShortAnnouncement> getShortAnnouncements() {
        return announcementService.getShortAnnouncements();
    }

    @GetMapping("/nearest")
    public List<Announcement> getNearestAnnouncements(double lat, double lng, int distance) {
        return announcementService.getNearestAnnouncements(lat, lng, distance);
    }
}
