package com.logger.logger_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USER_TRACKING")
public class UserTracking {

    @Id
    @Column(name = "id")
    private String id;

    @NotBlank(message = "User ID é obrigatório")
    @Size(max = 255)
    @Column(name = "user_id", nullable = false)
    private String userId;

    @NotNull(message = "Event Type é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Size(max = 500)
    @Column(name = "page_url")
    private String pageUrl;

    @Size(max = 255)
    @Column(name = "element_id")
    private String elementId;

    @Size(max = 50)
    @Column(name = "log_level")
    private String logLevel = "INFO";

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = java.util.UUID.randomUUID().toString();
        }
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }
}