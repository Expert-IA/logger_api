package com.logger.logger_api.infra.adapter;


import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import com.logger.logger_api.infra.jpa.JpaLoggerRepository;
import com.logger.logger_api.infra.Mapper.UserTrackingMapper;
import com.logger.logger_api.infra.Model.UserTrackingModel;
import java.lang.RuntimeException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


import static com.logger.logger_api.infra.Mapper.UserTrackingMapper.toEntity;

@Repository
public  class InteractionLoggerRepositoryAdapter implements InteractionLogRepository {

    private final JpaLoggerRepository jpaLoggerRepository;

    public InteractionLoggerRepositoryAdapter(JpaLoggerRepository jpaLoggerRepository) {
        this.jpaLoggerRepository = jpaLoggerRepository;
    }

    @Override
    public void Save(UserTracking interactionLog) {
        UserTrackingModel entitiy = toEntity(interactionLog);
        System.out.println(entitiy.getPageUrl());
        this.jpaLoggerRepository.save(entitiy);
    }

    @Override
    public void Delete(String userId) {
        this.jpaLoggerRepository.deleteById(Long.valueOf(userId));
    }


    @Override
    public void Update(UserTracking updatedTracking) {
        UserTrackingModel entity = toEntity(updatedTracking);
        jpaLoggerRepository.save(entity);
    }

    @Override
    public List<UserTracking> findAllByUserId(String userId) {
        List<UserTrackingModel> models = jpaLoggerRepository.findAllByUserId(userId);
        return models.stream()
                .map(UserTrackingMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public UserTracking findById(Long id) {
        UserTrackingModel model = jpaLoggerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserTracking with ID " + id + " not found"));

        System.out.println(model);
        return UserTrackingMapper.toDomain(model);
    }

}

