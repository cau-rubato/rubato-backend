package org.rubatophil.www.api.domain.mapping.concertMember;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("GUEST_CONCERT_MEMBER")
@Getter @Setter
public class GuestConcertMember extends ConcertMember {

    @NotNull
    private Integer salary;
}
