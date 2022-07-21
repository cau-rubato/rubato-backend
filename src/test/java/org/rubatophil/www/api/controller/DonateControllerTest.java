package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Donate;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DonateController.class)
class DonateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DonateService donateService;

    @BeforeEach
    public void setup() {

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
        verify(donateService).addNewDonate(any(), eq(budgetIds),eq(3L));
    }

}