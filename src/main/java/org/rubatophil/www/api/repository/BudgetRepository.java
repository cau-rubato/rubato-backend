package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
