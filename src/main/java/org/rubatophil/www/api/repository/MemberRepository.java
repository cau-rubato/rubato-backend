package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
