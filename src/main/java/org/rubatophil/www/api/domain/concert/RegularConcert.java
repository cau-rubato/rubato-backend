package org.rubatophil.www.api.domain.concert;

import lombok.*;
import org.rubatophil.www.api.domain.mapping.ConcertPamphlet;
import org.rubatophil.www.api.domain.mapping.concertPiece.ConcertPiece;
import org.rubatophil.www.api.domain.mapping.concertMember.ConcertMember;
import org.rubatophil.www.api.domain.mapping.concertPiece.RegularConcertPiece;
import org.rubatophil.www.api.domain.type.Location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("REGULAR_CONCERT")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegularConcert extends Concert {

    @NotNull
    private Integer episode;

    private Integer fee;

    @OneToMany(mappedBy = "regularConcert", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<RegularConcertPiece> regularConcertPieces = new ArrayList<>();

    public void addRegularConcertPiece(RegularConcertPiece regularConcertPiece) {
        this.regularConcertPieces.add(regularConcertPiece);
        regularConcertPiece.setRegularConcert(this);
    }

    @Builder
    public RegularConcert(String name, LocalDateTime date, Location location, String posterUrl, Integer episode, Integer fee) {
        super(name, date, location, posterUrl);
        this.episode = episode;
        this.fee = fee;
    }

    public void PrePersist() {
        super.PrePersist();
        this.fee = this.fee == null ? 0 : this.fee;
    }
}
