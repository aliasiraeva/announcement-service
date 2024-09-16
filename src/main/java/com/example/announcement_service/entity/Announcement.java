package com.example.announcement_service.entity;
import jakarta.persistence.*;
import liquibase.exception.DatabaseException;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private float lat;
    private float lng;
    private String phoneNumber;
    private LocalDateTime date;
    @OneToMany
    @JoinColumn(name = "announcement_id")
    private List<Image> images;
}
