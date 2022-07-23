package org.rubatophil.www.api.repository.member;

import org.rubatophil.www.api.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository<T extends Member> extends JpaRepository<T, Long> {
}
