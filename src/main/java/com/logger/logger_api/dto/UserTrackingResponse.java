package com.logger.logger_api.dto;

import com.logger.logger_api.model.EventType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTrackingResponse {

    private String id;
    private String userId;
    private EventType eventType;
    private String pageUrl;
    private String elementId;
    private String logLevel;
    private LocalDateTime timestamp;
    private LocalDateTime updatedAt;
}