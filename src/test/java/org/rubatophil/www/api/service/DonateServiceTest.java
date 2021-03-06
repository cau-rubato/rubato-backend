package org.rubatophil.www.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.repository.BudgetRepository;
import org.rubatophil.www.api.repository.DonateBudgetRepository;
import org.rubatophil.www.api.repository.DonateRepository;
import org.rubatophil.www.api.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(DonateService.class)
class DonateServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DonateRepository donateRepository;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    BudgetRepository budgetRepository;

    @MockBean
    DonateBudgetRepository donateBudgetRepository;

    @Autowired
    DonateService donateService;

    private Donate donate;
    private List<Long> budgetIds;
    List<Budget> budgets;

    private final Long memberId = 3L;

    @BeforeEach
    void setUp() {
        this.donate = Donate.builder()
                .message("test message")
                .amount(10000L)
                .build();


        this.budgetIds = new ArrayList<>();
        this.budgetIds.add(1L);
        this.budgetIds.add(2L);

        Optional<Member> member = Optional.of(ClubMember.builder()
                        .name("test clubMember")
                        .generation(33)
                        .build());

        when(this.memberRepository.findById(3L)).thenReturn(member);

        this.budgets = new ArrayList<>();

        Budget budget1 = Budget.builder()
                .name("budget1")
                .build();

        Budget budget2 = Budget.builder()
                .name("budget2")
                .build();

        this.budgets.add(budget1);
        this.budgets.add(budget2);

        when(this.budgetRepository.findAllById(this.budgetIds)).thenReturn(this.budgets);
        when(this.budgetRepository.findAll()).thenReturn(this.budgets);

    }

    @Test
    @DisplayName("addNewDonate")
    public void addNewDonateTest() throws Exception {
        //given
        //when
        this.donateService.addNewDonate(this.donate, this.budgetIds, this.memberId);

        //then
        verify(this.donateRepository).save(this.donate);
        verify(this.donateBudgetRepository, times(2)).save(any());
    }

    @Test
    @DisplayName("getAllDonates")
    public void getAllDonatesTest() throws Exception {
        //given
        List<Donate> donates = new ArrayList<>();

        donates.add(Donate.builder()
                .message("donate 1")
                .amount(100L)
                .build());

        donates.add(Donate.builder()
                .message("donate 2")
                .amount(200L)
                .build());

        donates.add(Donate.builder()
                .message("donate 3")
                .amount(300L)
                .build());

        when(this.donateRepository.findAll()).thenReturn(donates);


        //when
        List<Donate> result = this.donateService.getAllDonates();

        //then
        assertEquals(3, result.size());
    }

}