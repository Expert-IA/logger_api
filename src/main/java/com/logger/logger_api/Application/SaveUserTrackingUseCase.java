package com.logger.logger_api.Application;

import com.logger.logger_api.Controller.Dtos.UserTrackingDTO;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import com.logger.logger_api.infra.InteractionLoggerRepositoryAdapter;
import com.logger.logger_api.infra.Mapper.UserTrackingMapper;
import org.springframework.stereotype.Component;

@Component
public class SaveUserTrackingUseCase {

    private  final InteractionLogRepository repository;

    public SaveUserTrackingUseCase(InteractionLoggerRepositoryAdapter repository) {
        this.repository = repository;
    }


    public void execute(UserTrackingDTO userTrackingDto)
    {
        repository.Save( UserTrackingMapper.mapTOdomain(userTrackingDto));;
    }
}
