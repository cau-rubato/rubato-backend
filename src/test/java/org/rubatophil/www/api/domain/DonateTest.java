package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
class DonateTest {

    @PersistenceContext
    private EntityManager em;

    Donate donate;

    @BeforeEach
    void setUp() {
        this.donate = Donate.builder()
                .message("test donate message")
                .build();

        em.persist(this.donate);
    }

    @Test
    @DisplayName("Donate Builder가 잘 작동하는지 테스트")
    public void donateBuilderTest() throws Exception {

        //given
        //when
        //then
        Donate emfindDonate = em.find(Donate.class, this.donate.getId());

        assertEquals("test donate message", emfindDonate.getMessage());
    }

    @Test
    @DisplayName("Donate Table에 한 개의 DonateBudget이 잘 들어가고 조회되는지 테스트")
    public void addOneDonateBudgetTest() throws Exception {

        //given
        DonateBudget donateBudget = new DonateBudget();

        em.persist(donateBudget);

        //when
        this.donate.addDonateBudget(donateBudget);

        //then
        Donate emfindDonate = em.find(Donate.class, this.donate.getId());
        DonateBudget emfindDonateBudget = em.find(DonateBudget.class, donateBudget.getId());

        assertEquals(this.donate.getDonateBudgets().get(0), emfindDonate.getDonateBudgets().get(0));
        assertEquals(emfindDonate, emfindDonateBudget.getDonate());
    }
}