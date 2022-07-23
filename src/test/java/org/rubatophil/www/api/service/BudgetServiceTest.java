package org.rubatophil.www.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(BudgetService.class)
class BudgetServiceTest {

    @Autowired
    BudgetService budgetService;

    @MockBean
    BudgetRepository budgetRepository;

    List<Budget> budgets;

    @BeforeEach
    void setUp() {

        this.budgets = new ArrayList<>();

        Budget budget1 = Budget.builder()
                .name("budget1")
                .build();

        Budget budget2 = Budget.builder()
                .name("budget2")
                .build();

        this.budgets.add(budget1);
        this.budgets.add(budget2);

        when(this.budgetRepository.findAll()).thenReturn(this.budgets);

    }

    @Test
    @DisplayName("getAllBudgets")
    public void getAllBudgetsTest() throws Exception {
        //given

        //when
        List<Budget> result = this.budgetService.getAllBudgets();
        //then
        assertEquals(2, result.size());
        assertEquals(this.budgets, result);
    }

}