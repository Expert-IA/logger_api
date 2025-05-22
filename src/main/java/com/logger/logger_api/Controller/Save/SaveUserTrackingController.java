package com.logger.logger_api.Controller.Save;

import com.logger.logger_api.Application.SaveUserTrackingUseCase;
import com.logger.logger_api.Controller.Dtos.UserTrackingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("SaveTracking")
public class SaveUserTrackingController {


    private final SaveUserTrackingUseCase saveUserTrackingUseCase;

    public SaveUserTrackingController(SaveUserTrackingUseCase saveUserTrackingUseCase) {
        this.saveUserTrackingUseCase = saveUserTrackingUseCase;
    }


    @PostMapping()
    public ResponseEntity<Void> saveTracking(@RequestBody UserTrackingDTO dto) {
        saveUserTrackingUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
