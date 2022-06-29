package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.response.ApplyDurationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ApplyController {

    @GetMapping("/apply/check")
    public ApplyDurationResponse applyDuration() {
        ApplyDurationResponse applyDurationResponse = new ApplyDurationResponse(
                LocalDateTime.of(2022, 6, 25, 0, 0), LocalDateTime.of(2022, 6, 30, 0, 0)
        );

        return applyDurationResponse;
    }

}