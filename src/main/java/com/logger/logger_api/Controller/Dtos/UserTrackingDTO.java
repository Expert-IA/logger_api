package com.logger.logger_api.Controller.Dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserTrackingDTO {

    //private UUID id;
    private UUID userId;
    private String action;
    private LocalDateTime timestamp;

    public UserTrackingDTO() {
    }

    public UserTrackingDTO(UUID id, UUID userId, String action, LocalDateTime timestamp) {
      //  this.id = id;
        this.userId = userId;
        this.action = action;
        this.timestamp = timestamp;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
