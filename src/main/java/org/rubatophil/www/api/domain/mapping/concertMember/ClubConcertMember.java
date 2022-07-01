package org.rubatophil.www.api.domain.mapping.concertMember;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("CLUB_CONCERT_MEMBER")
@Getter @Setter
public class ClubConcertMember extends ConcertMember {

    @NotNull
    private Integer currentPaidFee;

    @PrePersist
    public void PrePersist() {
        this.currentPaidFee = this.currentPaidFee == null ? 0 : this.currentPaidFee;
    }
}
