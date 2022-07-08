package org.rubatophil.www.api.domain.type;

import lombok.*;
import org.rubatophil.www.api.domain.concert.Concert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LOCATION")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id @GeneratedValue
    @Column(name = "location_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Concert> concerts = new ArrayList<>();

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Location(String name, Address address, List<Concert> concerts) {
        this.name = name;
        this.address = address;
    }
}
