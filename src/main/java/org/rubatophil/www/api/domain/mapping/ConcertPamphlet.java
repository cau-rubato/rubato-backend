package org.rubatophil.www.api.domain.mapping;

import lombok.*;
import org.rubatophil.www.api.domain.concert.Concert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONCERT_PAMPHLET")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertPamphlet {

    @Id @GeneratedValue
    @Column(name = "concert_pamphlet_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    private Integer pageNumber;
    private String imageUrl;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public ConcertPamphlet(Integer pageNumber, String imageUrl) {
        this.pageNumber = pageNumber;
        this.imageUrl = imageUrl;
    }
}
