package org.rubatophil.www.api.domain.type;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Experience {

    @NotNull
    private Instrument instrument;
    @NotNull
    private Integer experienceYear;

    @Builder
    public Experience(Instrument instrument, Integer experienceYear) {
        this.instrument = instrument;
        this.experienceYear = experienceYear;
    }
}
