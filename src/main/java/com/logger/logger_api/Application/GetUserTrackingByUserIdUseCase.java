package com.logger.logger_api.Application;

import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.infra.InteractionLoggerRepositoryAdapter;

import java.util.List;
import java.util.UUID;

public class GetUserTrackingByUserIdUseCase {

    private  final InteractionLoggerRepositoryAdapter repository;

    public GetUserTrackingByUserIdUseCase(InteractionLoggerRepositoryAdapter repository){
        this.repository = repository;
    }

    public List<UserTracking> execute(UUID userId) {
        return repository.findByUserId(userId);
    }

}
