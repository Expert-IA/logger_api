package com.logger.logger_api.Controller.Save;

import com.logger.logger_api.Application.SaveUserTrackingUseCase;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("SaveTracking")
public class SaveUserTrackingController {

    private final SaveUserTrackingUseCase saveUserTrackingUseCase;

    public SaveUserTrackingController(SaveUserTrackingUseCase saveUserTrackingUseCase) {
        this.saveUserTrackingUseCase = saveUserTrackingUseCase;
    }




}
