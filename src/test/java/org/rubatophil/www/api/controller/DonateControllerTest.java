package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.service.BudgetService;
import org.rubatophil.www.api.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DonateController.class)
class DonateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DonateService donateService;

    @MockBean
    BudgetService budgetService;

    private Donate donate;
    private List<Budget> budgets;

    @BeforeEach
    void setUp() {
        this.donate = Donate.builder()
                .message("test message")
                .amount(10000L)
                .build();

        Optional<Member> member = Optional.of(ClubMember.builder()
                .name("test clubMember")
                .generation(33)
                .build());

        Budget budget1 = Budget.builder()
                .name("budget1")
                .build();

        Budget budget2 = Budget.builder()
                .name("budget2")
                .build();

        this.budgets = new ArrayList<>();
        this.budgets.add(budget1);
        this.budgets.add(budget2);

        when(this.budgetService.getAllBudgets()).thenReturn(this.budgets);

        DonateBudget donateBudget1 = new DonateBudget();
        DonateBudget donateBudget2 = new DonateBudget();

        budget1.addDonateBudget(donateBudget1);
        budget2.addDonateBudget(donateBudget2);

        this.donate.addDonateBudget(donateBudget1);
        this.donate.addDonateBudget(donateBudget2);

        member.get().addDonate(this.donate);

        List<Donate> donates = new ArrayList<>();
        donates.add(this.donate);
        when(this.donateService.getAllDonates()).thenReturn(donates);
    }

    @Test
    @DisplayName("postDonateInfo")
    public void postDonateInfoTest() throws Exception {
        //given
        String url = "/v1/donate";
        String newDonate = "{\"budgetIds\": [1, 2],\n" +
                "\"message\": \"test message\",\n" +
                "\"amount\": 100000,\n" +
                "\"memberId\": 3\n" +
                "}";

        List<Long> budgetIds = new ArrayList<>();
        budgetIds.add(1L);
        budgetIds.add(2L);

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newDonate)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isOk());
        verify(this.donateService).addNewDonate(any(), eq(budgetIds),eq(3L));
    }

    @Test
    @DisplayName("postDonateInfo with null amount")
    public void postDonateInfoTestWithNullAmount() throws Exception {
        //given
        String url = "/v1/donate";
        String newDonate = "{\"budgetIds\": [1, 2],\n" +
                "\"message\": \"test message\",\n" +
                "\"memberId\": 3\n" +
                "}";

        List<Long> budgetIds = new ArrayList<>();
        budgetIds.add(1L);
        budgetIds.add(2L);

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newDonate)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("getBudgetInfo")
    public void getBudgetInfo() throws Exception {
        //given
        String url = "/v1/donate/budgets";
        String expectedJson = "[{\"name\":\"budget1\"},{\"name\":\"budget2\"}]";

        //when
        ResultActions result = this.mockMvc.perform(get(url));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

    @Test
    @DisplayName("getDonateInfo")
    public void getDonateInfoTest() throws Exception {
        //given
        String url = "/v1/donate";
        String expectedJson = "[{\"name\":\"test clubMember\"}]";

        //when
        ResultActions result = this.mockMvc.perform(get(url));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

}