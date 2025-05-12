package com.logger.logger_api.infra.Model;


import com.logger.logger_api.Domain.valueObject.EventType;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name =  "user_tracking")
public class UserTrackingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_url", nullable = false)
    private String pageUrl;

    @Column(name = "event_type", length = 100)
    private EventType eventType;

    @Column(name = "element_id", length = 255)
    private String elementId;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;


    public UserTrackingModel(Long id, String pageUrl, EventType eventType, String elementId, LocalDateTime timestamp) {
    }

    public UserTrackingModel() {}

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
