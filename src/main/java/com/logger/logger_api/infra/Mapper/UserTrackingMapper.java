package com.logger.logger_api.infra.Mapper;

import com.logger.logger_api.Controller.Dtos.UserTrackingDTO;
import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.infra.Model.UserTrackingModel;

public class UserTrackingMapper {


    public static UserTracking mapTOdomain (UserTrackingDTO dto) {
        return new UserTracking(
                dto.getId(),
                dto.getPageUrl(),
                dto.getEventType(),
                dto.getElementId(),
                dto.getTimestamp(),
                dto.getLogLevel()
        );
    }

    public static UserTrackingModel toEntity(UserTracking domain) {
        return new UserTrackingModel(
                domain.getId(),
                domain.getPageUrl(),
                domain.getEventType(),
                domain.getElementId(),
                domain.getTimestamp(),
               domain.getLogLevel()
        );
    }
}
