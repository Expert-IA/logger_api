package com.logger.logger_api.Domain.Repository;

import com.logger.logger_api.Domain.Entity.UserTracking;

import java.util.List;
import java.util.UUID;


public interface InteractionLogRepository {

    void Save(UserTracking interactionLog);

    List<UserTracking> findByUserId(UUID userId);

    List<UserTracking> findAll();

}
