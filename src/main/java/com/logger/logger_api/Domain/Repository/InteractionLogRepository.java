package com.logger.logger_api.Domain.Repository;

import com.logger.logger_api.Domain.Entity.UserTrackingModel;

import java.util.List;
import java.util.UUID;

//isso
public interface InteractionLogRepository {

    void save(UserTrackingModel interactionLog);

    List<UserTrackingModel> findByUserId(UUID userId);

    List<UserTrackingModel> findAll();

}
