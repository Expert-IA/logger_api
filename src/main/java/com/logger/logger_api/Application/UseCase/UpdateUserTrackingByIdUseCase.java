package com.logger.logger_api.application;

import com.logger.logger_api.Application.Dtos.UserTrackingDTO;
import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import com.logger.logger_api.infra.Mapper.UserTrackingMapper;
import com.logger.logger_api.infra.Model.UserTrackingModel;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserTrackingByIdUseCase {

    private final InteractionLogRepository repository;

    public UpdateUserTrackingByIdUseCase(InteractionLogRepository repository) {
        this.repository = repository;
    }

    public void execute(String userId, UserTrackingDTO updatedTrackingDTO) {
        Long id = Long.valueOf(userId);

        UserTrackingModel existingModel = repository.findById(id);

        UserTracking updatedDomain = UserTrackingMapper.mapToDomain(updatedTrackingDTO);

        existingModel.setUserId(updatedDomain.getUserId());
        existingModel.setPageUrl(updatedDomain.getPageUrl());
        existingModel.setEventType(updatedDomain.getEventType());
        existingModel.setElementId(updatedDomain.getElementId());
        existingModel.setTimestamp(updatedDomain.getTimestamp());
        existingModel.setLogLevel(updatedDomain.getLogLevel());


        repository.Update(UserTrackingMapper.toDomain(existingModel));
    }
}
