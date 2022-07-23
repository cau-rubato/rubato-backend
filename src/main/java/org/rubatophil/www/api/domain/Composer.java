package org.rubatophil.www.api.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPOSER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Composer {

    @Id @GeneratedValue
    @Column(name = "composer_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "composer", cascade = CascadeType.ALL)
    private List<Piece> pieces = new ArrayList<>();

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Composer(String name) {
        this.name = name;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
        piece.setComposer(this);
    }

}
