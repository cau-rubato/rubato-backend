package org.rubatophil.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.FAQ;
import org.rubatophil.www.api.request.NewFAQ;
import org.rubatophil.www.api.response.faq.FAQResponse;
import org.rubatophil.www.api.service.FAQService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FAQController {

    private final FAQService faqService;

    @GetMapping("v1/faqs")
    public List<FAQResponse> getFAQInfo() {
        List<FAQResponse> faqList = new ArrayList<>();

        List<FAQ> serviceResult = this.faqService.getAllFAQs();

        for (FAQ result : serviceResult) {
            faqList.add(FAQResponse.builder()
                            .id(result.getId())
                            .question(result.getQuestion())
                            .answer(result.getAnswer())
                            .build()
            );
        }

        return faqList;
    }

    @PostMapping("v1/faqs")
    public void postFAQInfo(@Valid @RequestBody NewFAQ newFAQ) {
        FAQ faq = FAQ.builder()
                .question(newFAQ.getQuestion())
                .answer(newFAQ.getAnswer())
                .build();

        faqService.addNewFAQ(faq);
    }
}
