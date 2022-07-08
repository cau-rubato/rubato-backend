package org.rubatophil.www.api.domain.type;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String zipcode;
    private String state;        // 시, 도
    private String city;        // 시, 군, 구
    private String town;        // 읍, 면, 동
    private String fullAddress;

    @Builder
    public Address(String zipcode, String state, String city, String town, String fullAddress) {
        this.zipcode = zipcode;
        this.state = state;
        this.city = city;
        this.town = town;
        this.fullAddress = fullAddress;
    }
}
