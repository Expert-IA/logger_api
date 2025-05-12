package com.logger.logger_api.infra;

import com.logger.logger_api.Domain.Entity.UserTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaLoggerRepository extends JpaRepository<UserTracking, UUID> {

    UserTracking findUserById(UUID id);

}
