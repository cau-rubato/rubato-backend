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
        return faqRepository.findAll();
    }

    public FAQ addNewFAQ(FAQ faq) {
        faqRepository.save(faq);
        return faqRepository.findById(faq.getId()).get();
    }

}
