package org.rubatophil.www.api.repository.member;

import org.rubatophil.www.api.domain.member.GuestMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestMemberRepository extends JpaRepository<GuestMember, Long> {
}
