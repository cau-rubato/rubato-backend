package org.rubatophil.www.api.domain.member;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.MemberInstrument;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("GUEST_MEMBER")
@Getter @Setter
public class GuestMember extends Member {

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @NotNull
    private List<MemberInstrument> memberInstruments;
}
