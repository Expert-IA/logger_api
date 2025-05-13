package com.logger.logger_api.Application;

import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.infra.InteractionLoggerRepositoryAdapter;
import com.logger.logger_api.infra.Model.UserTrackingModel;

import static com.logger.logger_api.infra.Mapper.UserTrackingMapper.toEntity;

public class SaveUserTrackingUseCase {

    private  final InteractionLoggerRepositoryAdapter repository;

    public SaveUserTrackingUseCase(InteractionLoggerRepositoryAdapter repository) {
        this.repository = repository;
    }

    public void execute(UserTracking userTracking) {
        repository.Save(userTracking);
    }
}
