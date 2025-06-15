package com.logger.logger_api.Application.UseCase;

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
       try{
           Long id = Long.valueOf(userId);
           UserTracking existingLog = repository.findById(id);
           if (existingLog == null) {
               throw new RuntimeException("UserTracking not found with ID: " + userId);
           }

           UserTracking updatedLog = new UserTracking(
                   existingLog.getId(),
                   updatedTrackingDTO.getUserId(),
                   updatedTrackingDTO.getPageUrl(),
                   updatedTrackingDTO.getEventType(),
                   updatedTrackingDTO.getElementId(),
                   updatedTrackingDTO.getTimestamp(),
                   updatedTrackingDTO.getLogLevel()
           );

           repository.Update(updatedLog);
       } catch (Exception e) {
           {
               throw new RuntimeException("erro atualizando");
           }
       }

    }
}
