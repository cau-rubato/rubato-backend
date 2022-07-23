package org.rubatophil.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.request.DonateForm;
import org.rubatophil.www.api.response.donate.BudgetResponse;
import org.rubatophil.www.api.response.donate.DonateResponse;
import org.rubatophil.www.api.service.BudgetService;
import org.rubatophil.www.api.service.DonateService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DonateController {

    private final DonateService donateService;
    private final BudgetService budgetService;

    @GetMapping("v1/donate")
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

    @GetMapping("v1/donate/budgets")
    public List<BudgetResponse> getBudgetInfo() {
        List<BudgetResponse> budgetResponses = new ArrayList<>();

        List<Budget> budgets = this.budgetService.getAllBudgets();

        for (Budget budget : budgets) {
            budgetResponses.add(BudgetResponse.builder()
                    .name(budget.getName())
                    .build());
        }

        return budgetResponses;
    }

    @PostMapping("v1/donate")
    public void postDonateInfo(@Valid @RequestBody DonateForm donateForm) {
        Donate donate = Donate.builder()
                .amount(donateForm.getAmount())
                .message(donateForm.getMessage())
                .build();

        this.donateService.addNewDonate(donate, donateForm.getBudgetIds(), donateForm.getMemberId());
    }

}
