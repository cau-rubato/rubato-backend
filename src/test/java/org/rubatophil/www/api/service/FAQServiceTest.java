package org.rubatophil.www.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.FAQ;
import org.rubatophil.www.api.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(FAQService.class)
class FAQServiceTest {

    @Autowired
    FAQService faqService;

    @MockBean
    FAQRepository faqRepository;

    List<FAQ> faqs;
    FAQ faq;

    @BeforeEach
    void setUp() {

        this.faq = FAQ.builder()
                .question("test question")
                .answer("test answer")
                .build();
    }

    @Test
    @DisplayName("getAllFAQs")
    public void getAllFAQsTest() throws Exception {

        //given
        this.faqs = new ArrayList<>();
        this.faqs.add(this.faq);

        //when
        when(this.faqService.getAllFAQs()).thenReturn(this.faqs);

        //then
        assertEquals(this.faqs, this.faqService.getAllFAQs());
    }

    @Test
    @DisplayName("addNewFAQ")
    public void addNewFAQTest() throws Exception {

        //given
        //when
        this.faqService.addNewFAQ(this.faq);

        //then
        verify(this.faqRepository).save(this.faq);
    }
}