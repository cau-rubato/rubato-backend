package org.rubatophil.www.api.domain.member;

import lombok.*;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.type.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("GUEST_MEMBER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestMember extends Member {

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<MemberInstrument> memberInstruments = new ArrayList<>();

    @Builder
    public GuestMember(String name, LocalDate birth, String phoneNumber, Address address, String profileImage) {
        super(name, birth, phoneNumber, address, profileImage);
    }

    public void addMemberInstrument(MemberInstrument memberInstrument) {
        this.memberInstruments.add(memberInstrument);
        memberInstrument.setMember(this);
    }
}
