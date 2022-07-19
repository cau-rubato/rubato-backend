package org.rubatophil.www.api.repository.member;

import org.rubatophil.www.api.domain.member.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}
