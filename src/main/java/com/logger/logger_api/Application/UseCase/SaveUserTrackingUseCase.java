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

        System.out.println("pageUrl recebido: " + userTrackingDto.getPageUrl());
        if (userTrackingDto.getPageUrl() == null || userTrackingDto.getPageUrl().isEmpty()) {
            userTrackingDto.setPageUrl("unknown");
        }
       UserTracking teste =  mapToDomain(userTrackingDto);
        System.out.println(teste.getPageUrl());
        repository.Save(teste );
    }
}
