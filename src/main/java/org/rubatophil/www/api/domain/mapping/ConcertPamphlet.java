package org.rubatophil.www.api.domain.mapping;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.concert.Concert;
import org.rubatophil.www.api.domain.concert.RegularConcert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONCERT_PAMPHLET")
@Getter @Setter
public class ConcertPamphlet {

    @Id @GeneratedValue
    @Column(name = "concert_pamphlet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    @NotNull
    private Concert concert;

    @NotNull
    private Integer pageNumber;
    @NotNull
    private String imageUrl;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;
}
