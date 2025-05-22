package com.logger.logger_api.infra.Model;


import com.logger.logger_api.Domain.valueObject.EventType;
import jakarta.persistence.*;
import jakarta.persistence.Id;


import java.time.LocalDateTime;

@Entity
@Table(name =  "user_tracking")
public class UserTrackingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_url", length = 255)
    private String pageUrl;


    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", length = 100)
    private EventType eventType;

    @Column(name = "element_id", length = 255)
    private String elementId;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;

    @Column(name = "log_level", length  = 50  )
    private String logLevel;

    public UserTrackingModel() {}
    public UserTrackingModel(Long id, String pageUrl, EventType eventType, String elementId, LocalDateTime timestamp, String logLevel) {
        this.id = id;
        this.pageUrl = pageUrl;
        this.eventType = eventType;
        this.elementId = elementId;
        this.timestamp = timestamp;
        this.logLevel = logLevel;
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

    public String getLogLevel() {
        return logLevel;
    }
}
