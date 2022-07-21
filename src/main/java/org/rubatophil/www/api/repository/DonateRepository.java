package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonateRepository extends JpaRepository<Donate, Long> {
}
