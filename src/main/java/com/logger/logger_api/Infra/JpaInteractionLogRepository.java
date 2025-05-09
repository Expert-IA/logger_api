package com.logger.logger_api.Infra;

import com.logger.logger_api.Domain.Entity.UserTrackingModel;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class JpaInteractionLogRepository  implements InteractionLogRepository {

    private final SpringDataInteractionLogRepository jpaRepository;

    public JpaInteractionLogRepository(SpringDataInteractionLogRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(UserTrackingModel interactionLog) {
        this.jpaRepository.save(interactionLog);
    }

    @Override
    public List<UserTrackingModel> findByUserId(UUID userId) {
        this.jpaRepository.findByUserId(userId);
        return List.of();
    }

    @Override
    public List<UserTrackingModel> findAll() {
        this.jpaRepository.findAll();
        return List.of();
    }
}
