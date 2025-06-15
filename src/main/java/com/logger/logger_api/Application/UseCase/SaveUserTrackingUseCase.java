package com.logger.logger_api.Application.UseCase;

import com.logger.logger_api.Application.Dtos.UserTrackingDTO;
import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import com.logger.logger_api.infra.adapter.InteractionLoggerRepositoryAdapter;
import org.springframework.stereotype.Component;

import static com.logger.logger_api.infra.Mapper.UserTrackingMapper.mapToDomain;

@Component
public class SaveUserTrackingUseCase {

    private  final InteractionLogRepository repository;

    public SaveUserTrackingUseCase(InteractionLoggerRepositoryAdapter repository) {
        this.repository = repository;
    }


    public void execute(UserTrackingDTO userTrackingDto)
    {
        try{
            if (userTrackingDto.getPageUrl() == null || userTrackingDto.getPageUrl().isEmpty()) {
                userTrackingDto.setPageUrl("unknown");
            }
            UserTracking log =  mapToDomain(userTrackingDto);
            repository.Save(log );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário");
        }
    }
}
