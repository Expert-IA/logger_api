package com.logger.logger_api.Application.UseCase;

import com.logger.logger_api.Application.Dtos.UserTrackingDTO;
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
        System.out.println(mapToDomain(userTrackingDto));
        repository.Save( mapToDomain(userTrackingDto));;
    }
}
