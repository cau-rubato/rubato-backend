package org.rubatophil.www.api.domain.mapping;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.Piece;
import org.rubatophil.www.api.domain.concert.Concert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONCERT_PIECE")
@Getter @Setter
public class ConcertPiece {

    @Id @GeneratedValue
    @Column(name = "concert_piece_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    @NotNull
    private Concert concert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_id")
    @NotNull
    private Piece piece;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
