package org.rubatophil.www.api.domain.type;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter @Setter
public class Address {
    @NotNull
    private String zipcode;
    @NotNull
    private String city;
    @NotNull
    private String street;

    protected Address() {}

    @Builder
    public Address(String zipcode, String city, String street) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
    }
}
