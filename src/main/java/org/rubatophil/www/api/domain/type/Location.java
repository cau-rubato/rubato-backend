package org.rubatophil.www.api.domain.type;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
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
public class Location {

    @Id @GeneratedValue
    @Column(name = "location_id")
    private Long id;

    @NotNull
    private String name;

    @Embedded
    @NotNull
    private Address address;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Concert> concerts = new ArrayList<>();

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;
}
