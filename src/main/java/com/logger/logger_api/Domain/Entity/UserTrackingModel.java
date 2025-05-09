package com.logger.logger_api.Domain.Entity;

import com.logger.logger_api.Domain.valueObject.EventType;

import java.time.LocalDateTime;

public class UserTrackingModel {

    private Long id;
    private String pageUrl;
    private EventType eventType;
    private String elementId;
    private LocalDateTime timestamp;

    public UserTrackingModel(Long id, String pageUrl, EventType eventType, String elementId, LocalDateTime timestamp) {
        this.id = id;
        this.pageUrl = pageUrl;
        this.eventType = eventType;
        this.elementId = elementId;
        this.timestamp = timestamp;
    }

    public UserTrackingModel(String pageUrl, EventType eventType, String elementId) {
        this(null, pageUrl, eventType, elementId, LocalDateTime.now());
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getElementId() {
        return elementId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
