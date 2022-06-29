package org.rubatophil.www.api.domain.type;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Experience {

    @NotNull
    private Instrument instrument;
    @NotNull
    private Integer year;

    protected Experience() {}

    @Builder
    public Experience(Instrument instrument, Integer year) {
        this.instrument = instrument;
        this.year = year;
    }
}
