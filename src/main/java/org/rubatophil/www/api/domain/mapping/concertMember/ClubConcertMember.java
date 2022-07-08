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
@DiscriminatorValue("CLUB_CONCERT_MEMBER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubConcertMember extends ConcertMember {

    private Integer currentPaidFee;

    @Builder
    public ClubConcertMember(Member member, Concert concert, Instrument instrument, Part part, ConcertRole concertRole, Integer currentPaidFee) {
        super(member, concert, instrument, part, concertRole);
        this.currentPaidFee = currentPaidFee;
    }

    public void PrePersist() {
        super.PrePersist();
        this.currentPaidFee = this.currentPaidFee == null ? 0 : this.currentPaidFee;
    }
}
