package org.rubatophil.www.api.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.FAQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
class FAQRepositoryTest {

    @Autowired
    FAQRepository faqRepository;

    @Test
    @DisplayName("FAQ Table에 한개의 값이 잘 들어가고 조회되는지 테스트")
    public void oneFAQTest() throws Exception {
        //given
        FAQ faq = new FAQ();
        faq.setQuestion("질문");
        faq.setAnswer("답변");

        Long newId = faqRepository.saveAndFlush(faq).getId();

        //when
        FAQ newFaq = faqRepository.findById(newId).get();

        //then
        assertEquals(faq.getAnswer(), newFaq.getAnswer());
        assertEquals(faq.getQuestion(), newFaq.getQuestion());
    }

    @Test
    @DisplayName("FAQ Table에 여러개의 값이 잘 들어가고 조회되는지 테스트")
    public void multipleFAQTest() throws Exception {
        //given
        FAQ faq1 = new FAQ();
        faq1.setQuestion("질문1");
        faq1.setAnswer("답변1");

        FAQ faq2 = new FAQ();
        faq2.setQuestion("질문2");
        faq2.setAnswer("답변2");

        faqRepository.save(faq1);
        faqRepository.save(faq2);

        //when
        List<FAQ> faqs = faqRepository.findAll();

        //then
        assertEquals(faqs.size(), 2);
    }

    @Test
    @DisplayName("@NotNull 필드들이 Null일 때 Exception이 잘 발생하는지")
    public void nullFaqTest() throws Exception {
        //given
        FAQ faq = new FAQ();

        //then
        assertThrows(ConstraintViolationException.class, () -> {
            FAQ saved = faqRepository.saveAndFlush(faq);
        });
    }

}