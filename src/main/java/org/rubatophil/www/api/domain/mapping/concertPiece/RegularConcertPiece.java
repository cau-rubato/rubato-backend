package org.rubatophil.www.api.domain.mapping.concertPiece;

import lombok.*;
import org.rubatophil.www.api.domain.Piece;
import org.rubatophil.www.api.domain.concert.RegularConcert;
import org.rubatophil.www.api.domain.type.RegularConcertSection;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("REGULAR_CONCERT_PIECE")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegularConcertPiece extends ConcertPiece {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private RegularConcert regularConcert;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RegularConcertSection section;

    @Builder
    public RegularConcertPiece(RegularConcertSection section) {
        this.section = section;
    }
}
