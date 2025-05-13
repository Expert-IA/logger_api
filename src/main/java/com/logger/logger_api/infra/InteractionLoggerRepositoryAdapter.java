package com.logger.logger_api.infra;


import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import com.logger.logger_api.infra.Mapper.UserTrackingMapper;
import com.logger.logger_api.infra.Model.UserTrackingModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.logger.logger_api.infra.Mapper.UserTrackingMapper.toEntity;

@Repository
public class InteractionLoggerRepositoryAdapter implements InteractionLogRepository {

    private final JpaLoggerRepository jpaLoggerRepository;

    public InteractionLoggerRepositoryAdapter(JpaLoggerRepository jpaLoggerRepository) {
        this.jpaLoggerRepository = jpaLoggerRepository;
    }

    @Override
    public void Save(UserTracking interactionLog) {
        UserTrackingModel entitiy = toEntity(interactionLog);
        this.jpaLoggerRepository.save(entitiy);
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
