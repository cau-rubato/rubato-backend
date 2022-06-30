package org.rubatophil.www.api.domain.mapping.concertMember;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.concert.Concert;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.domain.type.ConcertRole;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.type.Part;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONCERT_MEMBER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public class ConcertMember {

    @Id @GeneratedValue
    @Column(name = "concert_member_id")
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
    @NotNull
    private Part part;
    @Enumerated(EnumType.STRING)
    @NotNull
    private ConcertRole concertRole;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;
}
