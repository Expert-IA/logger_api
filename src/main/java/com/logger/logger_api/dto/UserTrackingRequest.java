package com.logger.logger_api.dto;

import com.logger.logger_api.model.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserTrackingRequest {

    @NotBlank(message = "User ID é obrigatório")
    @Size(max = 255)
    private String userId;

    @NotNull(message = "Event Type é obrigatório")
    private EventType eventType;

    @Size(max = 500)
    private String pageUrl;

    @Size(max = 255)
    private String elementId;

    @Size(max = 50)
    private String logLevel;
}