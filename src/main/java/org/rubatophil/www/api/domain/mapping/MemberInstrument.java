package org.rubatophil.www.api.domain.mapping;

import lombok.*;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER_INSTRUMENT")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInstrument {

    @Id @GeneratedValue
    @Column(name = "member_instrument_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Instrument instrument;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public MemberInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
