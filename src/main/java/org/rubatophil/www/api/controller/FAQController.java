package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.response.faq.FAQResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FAQController {
    @GetMapping("/faqs")
    public List<FAQResponse> faqInfo() {
        List<FAQResponse> faqList = new ArrayList<>();

        FAQResponse faq1 = FAQResponse.builder().
                id(0L).
                question("몇살이에요?").
                answer("25살").
                build();

        FAQResponse faq2 = FAQResponse.builder().
                id(1L).
                question("어떻게 가입해요?").
                answer("몰라요").
                build();

        faqList.add(faq1);
        faqList.add(faq2);

        return faqList;
    }
}
