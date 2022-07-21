package org.rubatophil.www.api.repository.member;

import org.rubatophil.www.api.domain.member.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
}
