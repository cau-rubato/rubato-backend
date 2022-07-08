package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
class FAQTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("FAQ Builder가 잘 동작하고 저장되는지 테스트")
    public void oneFAQTest() throws Exception {
        //given
        FAQ faq = FAQ.builder().question("Q").answer("A").build();

        //when
        em.persist(faq);
        Long faqId = faq.getId();
        FAQ newFaq = em.find(FAQ.class, faqId);

        //then
        assertEquals(faq.getAnswer(), newFaq.getAnswer());
        assertEquals(faq.getQuestion(), newFaq.getQuestion());
    }

}