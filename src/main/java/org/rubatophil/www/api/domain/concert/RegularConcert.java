package org.rubatophil.www.api.domain.concert;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.mapping.ConcertPamphlet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("REGULAR_CONCERT")
@Getter @Setter
public class RegularConcert extends Concert {

    @NotNull
    private Integer episode;

    @OneToMany(mappedBy = "regularConcert", cascade = CascadeType.ALL)
    private List<ConcertPamphlet> concertPamphlets = new ArrayList<>();

}
