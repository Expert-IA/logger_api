package com.logger.logger_api.Domain.Repository;

import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.infra.Model.UserTrackingModel;

import java.util.List;


public interface InteractionLogRepository {
    void Save(UserTracking interactionLog);
    void Delete(String userId);
    void Update(UserTracking updatedTracking);
    List<UserTracking> findAllByUserId(String userId);
    UserTracking findById(Long id);
}
