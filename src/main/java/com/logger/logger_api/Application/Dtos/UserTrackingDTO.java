package com.logger.logger_api.Application.Dtos;

import com.logger.logger_api.Domain.valueObject.EventType;

import java.time.LocalDateTime;

public class UserTrackingDTO {

    private Long id;
    private String UserId;
    private String action;
    private LocalDateTime timestamp;
    private final String elementId;;
    private String pageUrl;
    private  EventType eventType;
    private String logLevel;


    public UserTrackingDTO(Long id,String UserId ,String pageUrl, EventType eventType, String elementId, LocalDateTime timestamp, String logLevel  ) {
        this.id = id;
        this.UserId = UserId;
        this.pageUrl = pageUrl;
        this.eventType = eventType;
        this.elementId = elementId;
        this.timestamp = timestamp;
        this.logLevel = logLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getElementId() {
        return elementId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
