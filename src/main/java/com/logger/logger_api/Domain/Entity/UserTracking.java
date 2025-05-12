package com.logger.logger_api.Domain.Entity;

import com.logger.logger_api.Domain.valueObject.EventType;
import java.time.LocalDateTime;

public class UserTracking {

    private final Long id;
    private final String pageUrl;
    private final EventType eventType;
    private final String elementId;
    private final LocalDateTime timestamp;

    public UserTracking(Long id, String pageUrl, EventType eventType, String elementId, LocalDateTime timestamp) {
        this.id = id;
        this.pageUrl = pageUrl;
        this.eventType = eventType;
        this.elementId = elementId;
        this.timestamp = timestamp;
    }


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
