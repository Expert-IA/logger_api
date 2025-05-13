package com.logger.logger_api.Controller;
import com.logger.logger_api.Application.SaveUserTrackingUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userTracking")
public class LoggerController {

    @GetMapping
    public String healthCheck() {
        return "Ok";
    }

}
