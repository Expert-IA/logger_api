package com.logger.logger_api.Infra;

import com.logger.logger_api.Domain.Entity.UserTrackingModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataInteractionLogRepository  extends JpaRepository<UserTrackingModel, UUID> {
    List<UserTrackingModel> findByUserId(UUID userId);
}
