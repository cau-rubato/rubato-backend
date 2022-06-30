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

        faqList.add(new FAQResponse(0L, "몇살이에요?", "25살"));
        faqList.add(new FAQResponse(1L, "어떻게 가입해요?", "몰라요"));

        return faqList;
    }
}
