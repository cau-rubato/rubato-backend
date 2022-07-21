package org.rubatophil.www.api.repository.member;

import org.rubatophil.www.api.domain.member.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
