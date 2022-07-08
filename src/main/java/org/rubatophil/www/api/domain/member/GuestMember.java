package org.rubatophil.www.api.domain.member;

import lombok.*;
import org.rubatophil.www.api.domain.Account;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.type.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("GUEST_MEMBER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestMember extends Member {

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberInstrument> memberInstruments;

    @Builder
    public GuestMember(Account account, String name, LocalDate birth, String phoneNumber, Address address, List<MemberInstrument> memberInstruments) {
        super(account, name, birth, phoneNumber, address);
    }
}
