package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
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

    private Donate donate;

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


        DonateBudget donateBudget1 = new DonateBudget();
        DonateBudget donateBudget2 = new DonateBudget();

        budget1.addDonateBudget(donateBudget1);
        budget2.addDonateBudget(donateBudget2);

        this.donate.addDonateBudget(donateBudget1);
        this.donate.addDonateBudget(donateBudget2);

        member.get().addDonate(this.donate);

        List<Donate> donates = new ArrayList<>();
        donates.add(donate);
        when(donateService.getAllDonates()).thenReturn(donates);
    }

    @Test
    @DisplayName("postDonateInfo")
    public void postDonateInfoTest() throws Exception {
        //given
        String url = "/donate";
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
    @DisplayName("getDonateInfo")
    public void getDonateInfoTest() throws Exception {
        //given
        String url = "/donate";
        String expectedJson = "[{\"name\":\"test clubMember\"}]";

        //when
        ResultActions result = this.mockMvc.perform(get(url));

        //then
        result.andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

}