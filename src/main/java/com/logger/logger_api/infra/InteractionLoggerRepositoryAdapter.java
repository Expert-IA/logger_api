package com.logger.logger_api.infra;

import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class InteractionLoggerRepositoryAdapter implements InteractionLogRepository {

    private final JpaLoggerRepository jpaLoggerRepository;

    public InteractionLoggerRepositoryAdapter(JpaLoggerRepository jpaLoggerRepository) {
        this.jpaLoggerRepository = jpaLoggerRepository;
    }


    @Override
    public void save(UserTracking interactionLog) {
        this.jpaLoggerRepository.save(interactionLog);
    }

    @Override
    public List<UserTracking> findByUserId(UUID userId) {
        this.jpaLoggerRepository.findUserById(userId);
        return List.of();
    }

    @Override
    public List<UserTracking> findAll() {
        this.jpaLoggerRepository.findAll();
        return List.of();
    }
}
