package org.rubatophil.www.api.domain.concert;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("REGULAR_CONCERT")
@Getter @Setter
public class RegularConcert extends Concert {

    @NotNull
    private Integer episode;

    private Integer fee;
}
