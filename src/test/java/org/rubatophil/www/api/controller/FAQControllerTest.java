package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.FAQ;
import org.rubatophil.www.api.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FAQController.class)
class FAQControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FAQService faqService;
    List<FAQ> faqs;
    FAQ faq;

    @BeforeEach
    public void setup() {

        this.faq = FAQ.builder()
                .question("test question")
                .answer("test answer")
                .build();
    }

    @Test
    @DisplayName("getFAQInfo")
    public void getFAQInfoTest() throws Exception {

        //given
        String url = "/v1/faqs";
        String expectedJson = "[{\"id\":null,\"question\":\"test question\",\"answer\":\"test answer\"}]";

        this.faqs = new ArrayList<>();
        this.faqs.add(this.faq);

        //when
        when(this.faqService.getAllFAQs()).thenReturn(this.faqs);

        ResultActions mvcResult = this.mockMvc.perform(get(url));

        //then
        assertEquals(this.faqs, this.faqService.getAllFAQs());

        mvcResult.andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

    @Test
    @DisplayName("postFAQInfo")
    public void postFAQInfoTest() throws Exception {

        //given
        String url = "/v1/faqs";
        String newFAQ = "{\"question\":\"test question\",\"answer\":\"test answer\"}";

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newFAQ)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isOk());
    }

    @Test
    @DisplayName("[postFAQInfo] 하나의 request field가 null")
    public void postFAQInfoNullTest() throws Exception {

        //given
        String url = "/v1/faqs";
        String newFAQ = "{\"answer\":\"test answer\"}";

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newFAQ)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isBadRequest());
    }
}