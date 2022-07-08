package org.rubatophil.www.api.domain.mapping.concertMember;

import lombok.*;
import org.rubatophil.www.api.domain.concert.Concert;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.domain.type.ConcertRole;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.type.Part;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GUEST_CONCERT_MEMBER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestConcertMember extends ConcertMember {

    private Integer salary;
    @Builder
    public GuestConcertMember(Member member, Concert concert, Instrument instrument, Part part, ConcertRole concertRole, Integer salary) {
        super(member, concert, instrument, part, concertRole);
        this.salary = salary;
    }
}
