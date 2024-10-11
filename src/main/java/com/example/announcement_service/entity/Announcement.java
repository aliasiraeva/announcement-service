package com.example.announcement_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Поле не должно быть пустым")
    private String text;
    private Double lat;
    private Double lng;
    private String address;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 10, max = 10, message = "Некорректный номер телефона")
    private String phoneNumber;
    private LocalDateTime date;
    @OneToMany
    @JoinColumn(name = "announcement_id")
    private List<Image> images;

    public Announcement() {}
}
