package org.rubatophil.www.api.domain.mapping;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.member.Member;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER_INSTRUMENT")
@Getter @Setter
public class MemberInstrument {

    @Id @GeneratedValue
    @Column(name = "member_instrument_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Instrument instrument;
}
