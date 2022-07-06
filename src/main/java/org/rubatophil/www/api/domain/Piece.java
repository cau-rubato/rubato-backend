package org.rubatophil.www.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.mapping.ConcertPiece;
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
public class Piece {

    @Id @GeneratedValue
    @Column(name = "piece_id")
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
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
