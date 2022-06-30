package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.request.ApplyForm;
import org.rubatophil.www.api.request.DonateForm;
import org.rubatophil.www.api.response.ApplyStateResponse;
import org.rubatophil.www.api.response.DonateResponse;
import org.rubatophil.www.api.response.DonateStateResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DonateController {

    @GetMapping("/donate")
    public List<DonateResponse> donateInfo() {

        List<DonateResponse> donateResponses = new ArrayList<>();

        donateResponses.add(new DonateResponse("백승윤"));
        donateResponses.add(new DonateResponse("김혜성"));

        return donateResponses;

    }

    @PostMapping("/donate")
    public DonateStateResponse donate(@RequestBody DonateForm donateForm) {
        DonateStateResponse donateStateResponse = new DonateStateResponse("OK");
        return donateStateResponse;
    }

}
