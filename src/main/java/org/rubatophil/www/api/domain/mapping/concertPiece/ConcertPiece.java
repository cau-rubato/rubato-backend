package org.rubatophil.www.api.domain.mapping.concertPiece;

import lombok.*;
import org.rubatophil.www.api.domain.Piece;
import org.rubatophil.www.api.domain.concert.Concert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONCERT_PIECE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ConcertPiece {

    @Id @GeneratedValue
    @Column(name = "concert_piece_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_id")
    private Piece piece;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

}
