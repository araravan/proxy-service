package com.example.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AppConfig {
    @ConfigProperty(name = "app.original-url")
    String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }
}
