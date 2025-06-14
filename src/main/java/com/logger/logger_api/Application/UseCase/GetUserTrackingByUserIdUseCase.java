package com.logger.logger_api.Application.UseCase;

import com.logger.logger_api.Application.Dtos.UserTrackingDTO;
import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUserTrackingByUserIdUseCase {

    private  final InteractionLogRepository repository;

    public GetUserTrackingByUserIdUseCase(InteractionLogRepository repository){
        this.repository = repository;
    }

    public List<UserTrackingDTO> execute(String userId) {

        try {
            List<UserTracking> entities = repository.findAllByUserId(userId);

            if (entities.isEmpty()){
                throw new RuntimeException("Nenhum usuário encontrado");
            }

            return entities.stream()
                    .map(entity -> new UserTrackingDTO(
                            entity.getId(),
                            entity.getUserId(),
                            entity.getPageUrl(),
                            entity.getEventType(),
                            entity.getElementId(),
                            entity.getTimestamp(),
                            entity.getLogLevel()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
