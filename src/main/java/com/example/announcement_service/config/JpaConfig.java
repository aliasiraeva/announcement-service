package com.example.announcement_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.announcement_service.repository")
@Configuration
public class JpaConfig {
}
