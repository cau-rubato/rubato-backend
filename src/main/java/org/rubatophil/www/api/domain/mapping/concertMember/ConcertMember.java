package org.rubatophil.www.api.domain.mapping.concertMember;

import lombok.*;
import org.rubatophil.www.api.domain.concert.Concert;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.domain.type.ConcertRole;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.type.Part;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONCERT_MEMBER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ConcertMember {

    @Id @GeneratedValue
    @Column(name = "concert_member_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    @NotNull
    private Concert concert;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Instrument instrument;
    @Enumerated(EnumType.STRING)
    private Part part;
    @Enumerated(EnumType.STRING)
    private ConcertRole concertRole;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    public ConcertMember(Member member, Concert concert, Instrument instrument, Part part, ConcertRole concertRole) {
        this.member = member;
        this.concert = concert;
        this.instrument = instrument;
        this.part = part;
        this.concertRole = concertRole;
    }

    @PrePersist
    public void PrePersist() {
        this.concertRole = this.concertRole == null ? ConcertRole.GENERAL : this.concertRole;
    }
}
