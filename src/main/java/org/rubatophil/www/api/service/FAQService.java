package org.rubatophil.www.api.service;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.FAQ;
import org.rubatophil.www.api.repository.FAQRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FAQService {

    private final FAQRepository faqRepository;

    public List<FAQ> getAllFAQs() {
        return this.faqRepository.findAll();
    }

    public void addNewFAQ(FAQ faq) {
        this.faqRepository.save(faq);
    }

}
