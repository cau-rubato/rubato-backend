package org.rubatophil.www.api.repository;


import org.rubatophil.www.api.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
}
