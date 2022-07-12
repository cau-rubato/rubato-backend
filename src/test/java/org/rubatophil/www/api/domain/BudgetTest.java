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
class BudgetTest {

    @PersistenceContext
    private EntityManager em;

    Budget budget;

    @BeforeEach
    void setUp() {
        this.budget = Budget.builder()
                .name("test budget name")
                .build();

        em.persist(budget);
    }

    @Test
    @DisplayName("Budget Builder가 잘 작동하는지 테스트")
    public void budgetBuilderTest() throws Exception {

        //given
        //when
        //then
        Budget emfindBudget = em.find(Budget.class, this.budget.getId());

        assertEquals("test budget name", emfindBudget.getName());
    }

    @Test
    @DisplayName("Budget Table에 한 개의 DonateBudget이 잘 들어가고 조회되는지 테스트")
    public void addOneDonateBudget() throws Exception {

        //given
        DonateBudget donateBudget = new DonateBudget();

        em.persist(donateBudget);

        //when
        this.budget.addDonateBudget(donateBudget);

        //then
        Budget emfindBudget = em.find(Budget.class, this.budget.getId());
        DonateBudget emfindDonateBudget = em.find(DonateBudget.class, donateBudget.getId());

        assertEquals(this.budget.getDonateBudgets().get(0), emfindBudget.getDonateBudgets().get(0));
        assertEquals(emfindBudget, emfindDonateBudget.getBudget());
    }
}