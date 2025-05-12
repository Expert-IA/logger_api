package com.logger.logger_api.infra.Mapper;

import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.infra.Model.UserTrackingModel;

public class UserTrackingMapper {

    public static UserTracking toDomain(UserTrackingModel model) {
        return new UserTracking(
                model.getId(),
                model.getPageUrl(),
                model.getEventType(),
                model.getElementId(),
                model.getTimestamp()
        );
    }

    public static UserTrackingModel toEntity(UserTracking domain) {
        return new UserTrackingModel(
                domain.getId(),
                domain.getPageUrl(),
                domain.getEventType(),
                domain.getElementId(),
                domain.getTimestamp()
        );
    }
}
