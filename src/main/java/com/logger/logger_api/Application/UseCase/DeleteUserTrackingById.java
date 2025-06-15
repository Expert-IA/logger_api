package com.logger.logger_api.Application.UseCase;

import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserTrackingById {

    private  final InteractionLogRepository repository;

    public DeleteUserTrackingById(InteractionLogRepository repository) {
        this.repository = repository;
    }

    public void execute(String userId){
        try{
            Long id = Long.parseLong(userId);
            UserTracking entity = repository.findById(id);
            if (entity == null) {
                throw new RuntimeException("No UserTracking found for ID: " + userId);
            }
        }catch (Exception e){
             throw new RuntimeException("erro ao deletar usuário");
        }


    }
}
