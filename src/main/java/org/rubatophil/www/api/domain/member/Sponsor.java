package org.rubatophil.www.api.domain.member;

import lombok.*;
import org.rubatophil.www.api.domain.Account;
import org.rubatophil.www.api.domain.type.Address;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("SPONSOR")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sponsor extends Member {

    @Builder
    public Sponsor(Account account, String name, LocalDate birth, String phoneNumber, Address address) {
        super(account, name, birth, phoneNumber, address);
    }
}
