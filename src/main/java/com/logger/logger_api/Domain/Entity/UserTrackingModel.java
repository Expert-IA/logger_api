package com.logger.logger_api.Domain.Entity;

import com.logger.logger_api.Domain.valueObject.EventType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name =  "user_tracking")
@Access(AccessType.FIELD)
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

}

