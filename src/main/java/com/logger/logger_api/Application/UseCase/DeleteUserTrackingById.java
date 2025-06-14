package com.logger.logger_api.Application.UseCase;

import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserTrackingById {

    private  final InteractionLogRepository repository;

    public DeleteUserTrackingById(InteractionLogRepository repository) {
        this.repository = repository;
    }

    public void execute(String userId){
        this.repository.Delete(userId);
        return;
    }
}
