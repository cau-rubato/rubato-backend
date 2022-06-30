package org.rubatophil.www.api.domain.mapping.concertMember;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLUB_CONCERT_MEMBER")
@Getter @Setter
public class ClubConcertMember extends ConcertMember {}
