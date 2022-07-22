package org.rubatophil.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.repository.MemberRepository;
import org.rubatophil.www.api.request.DonateForm;
import org.rubatophil.www.api.response.donate.DonateResponse;
import org.rubatophil.www.api.response.donate.DonateStatusResponse;
import org.rubatophil.www.api.service.DonateService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DonateController {

    private final DonateService donateService;

    @GetMapping("/donate")
    public List<DonateResponse> getDonateInfo() {

        List<DonateResponse> donateResponses = new ArrayList<>();

        List<Donate> donates = this.donateService.getAllDonates();

        for (Donate donate : donates) {
            donateResponses.add(DonateResponse.builder()
                    .name(donate.getMember().getName())

                    .build());
        }

        return donateResponses;

    }

    @PostMapping("/donate")
    public void postDonateInfo(@Valid @RequestBody DonateForm donateForm) {
        Donate donate = Donate.builder()
                .amount(donateForm.getAmount())
                .message(donateForm.getMessage())
                .build();

        donateService.addNewDonate(donate, donateForm.getBudgetIds(), donateForm.getMemberId());
    }

}
