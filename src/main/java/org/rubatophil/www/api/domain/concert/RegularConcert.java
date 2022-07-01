package org.rubatophil.www.api.domain.concert;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("REGULAR_CONCERT")
@Getter @Setter
public class RegularConcert extends Concert {

    @NotNull
    private Integer episode;

    private Integer fee;
}
