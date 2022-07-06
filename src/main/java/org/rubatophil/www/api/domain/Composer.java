package org.rubatophil.www.api.domain;

import lombok.Getter;
import lombok.Setter;
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
public class Composer {

    @Id @GeneratedValue
    @Column(name = "composer_id")
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "composer", cascade = CascadeType.ALL)
    private List<Piece> pieces = new ArrayList<>();

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
