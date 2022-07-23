package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplyController.class)
class ApplyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
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