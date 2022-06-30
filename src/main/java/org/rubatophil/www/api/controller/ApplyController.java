package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.request.ApplyForm;
import org.rubatophil.www.api.response.apply.ApplyDurationResponse;
import org.rubatophil.www.api.response.apply.ApplyStatusResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ApplyController {

    @GetMapping("/apply/check")
    public ApplyDurationResponse applyDuration() {
        ApplyDurationResponse applyDurationResponse = ApplyDurationResponse.builder()
                .start(LocalDateTime.of(2022, 6, 25, 0, 0))
                .end(LocalDateTime.of(2022, 6, 30, 0, 0))
                .build();
        return applyDurationResponse;
    }

    @PostMapping("/apply")
    public ApplyStatusResponse apply(@RequestParam(value="apply_type") String applyType, @RequestBody ApplyForm applyForm) {
        ApplyStatusResponse applyStateResponse = ApplyStatusResponse.builder()
                .status("OK")
                .build();
        return applyStateResponse;
    }

}