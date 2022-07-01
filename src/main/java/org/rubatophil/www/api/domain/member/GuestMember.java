package org.rubatophil.www.api.domain.member;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@DiscriminatorValue("GUEST_MEMBER")
@Getter @Setter
public class GuestMember extends Member {

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @NotNull
    private List<MemberInstrument> memberInstruments;
}
