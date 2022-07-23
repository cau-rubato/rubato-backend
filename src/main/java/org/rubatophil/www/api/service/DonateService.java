package org.rubatophil.www.api.service;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.repository.BudgetRepository;
import org.rubatophil.www.api.repository.DonateBudgetRepository;
import org.rubatophil.www.api.repository.DonateRepository;
import org.rubatophil.www.api.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonateService {

    private final DonateRepository donateRepository;
    private final MemberRepository memberRepository;
    private final BudgetRepository budgetRepository;
    private final DonateBudgetRepository donateBudgetRepository;

    public void addNewDonate(Donate donate, List<Long> budgetIds, Long memberId) {
        Member member = (Member) this.memberRepository.findById(memberId).get();
        List<Budget> budgets = this.budgetRepository.findAllById(budgetIds);

        this.donateRepository.save(donate);

        for (Budget budget : budgets) {
            DonateBudget donateBudget = new DonateBudget();

            this.donateBudgetRepository.save(donateBudget);

            donate.addDonateBudget(donateBudget);
            budget.addDonateBudget(donateBudget);
        }

        member.addDonate(donate);
    }

    public List<Donate> getAllDonates() {
        return this.donateRepository.findAll();
    }
}
