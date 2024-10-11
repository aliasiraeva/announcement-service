package com.example.announcement_service.controller;

import com.example.announcement_service.entity.Announcement;
import com.example.announcement_service.model.ShortAnnouncement;
import com.example.announcement_service.service.AnnouncementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    public void postAnnouncement(@Valid @RequestBody Announcement announcement) {
        announcementService.saveAnnouncement(announcement);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
    }

    @PutMapping("/{id}")
    public void putAnnouncement(@PathVariable Integer id, @Valid @RequestBody Announcement announcement) {
        announcementService.updateAnnouncement(id, announcement);
    }

    @GetMapping("/short")
    public List<ShortAnnouncement> getShortAnnouncements() {
        return announcementService.getShortAnnouncements();
    }

    @GetMapping("/nearest")
    public List<Announcement> getNearestAnnouncements(@NotNull double lat, @NotNull double lng, @Size(min=10, max=10000) int distance) {
        return announcementService.getNearestAnnouncements(lat, lng, distance);
    }
}
