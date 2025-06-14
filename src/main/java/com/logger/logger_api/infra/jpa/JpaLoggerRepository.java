package com.logger.logger_api.infra.jpa;


import com.logger.logger_api.infra.Model.UserTrackingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JpaLoggerRepository extends JpaRepository<UserTrackingModel, Long> {
    List<UserTrackingModel> findAllByUserId(String userId);
}
