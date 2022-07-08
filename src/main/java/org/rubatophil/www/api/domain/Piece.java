package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.mapping.concertPiece.ConcertPiece;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PIECE")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Piece {

    @Id @GeneratedValue
    @Column(name = "piece_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "composer_id")
    @NotNull
    private Composer composer;

    @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL)
    private List<ConcertPiece> concertPieces = new ArrayList<>();

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Piece(String name, Composer composer) {
        this.name = name;
        this.composer = composer;
    }

    public void addConcertPiece(ConcertPiece concertPiece) {
        this.concertPieces.add(concertPiece);
        concertPiece.setPiece(this);
    }
}
