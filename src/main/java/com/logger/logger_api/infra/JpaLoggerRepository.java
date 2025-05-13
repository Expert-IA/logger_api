package com.logger.logger_api.infra;

import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.infra.Model.UserTrackingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaLoggerRepository extends JpaRepository<UserTrackingModel, Long> {

    UserTracking findUserById(UUID id);

}
