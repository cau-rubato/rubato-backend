package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplyController.class)
@Import(HttpEncodingAutoConfiguration.class)
class ApplyControllerTest {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    public void applyDuration() throws Exception {
        //given
        String url = "/apply/check";
        String expectedJson = "{\"start\":\"2022-06-25T00:00:00\",\"end\":\"2022-06-30T00:00:00\"}";

        //when
        ResultActions mvcResult = mockMvc.perform(get(url));

        //then
        mvcResult.andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

    @Test
    public void apply() throws Exception {

    }
}