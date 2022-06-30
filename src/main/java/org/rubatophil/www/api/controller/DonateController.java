package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.request.DonateForm;
import org.rubatophil.www.api.response.donate.DonateResponse;
import org.rubatophil.www.api.response.donate.DonateStatusResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DonateController {

    @GetMapping("/donate")
    public List<DonateResponse> donateInfo() {

        List<DonateResponse> donateResponses = new ArrayList<>();

        donateResponses.add(DonateResponse.builder().name("백승윤").build());
        donateResponses.add(DonateResponse.builder().name("김혜성").build());

        return donateResponses;

    }

    @PostMapping("/donate")
    public DonateStatusResponse donate(@RequestBody DonateForm donateForm) {
        DonateStatusResponse donateStatusResponse = DonateStatusResponse.builder().status("OK").build();
        return donateStatusResponse;
    }

}
