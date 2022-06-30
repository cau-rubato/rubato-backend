package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.request.ApplyForm;
import org.rubatophil.www.api.response.ApplyDurationResponse;
import org.rubatophil.www.api.response.ApplyStateResponse;
import org.rubatophil.www.api.response.FAQResponse;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/apply")
    public ApplyStateResponse apply(@RequestParam(value="apply_type") String applyType, @RequestBody ApplyForm applyForm) {
        ApplyStateResponse applyStateResponse = new ApplyStateResponse("OK");
        return applyStateResponse;
    }

}