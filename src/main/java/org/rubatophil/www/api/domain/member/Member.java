package org.rubatophil.www.api.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rubatophil.www.api.domain.Account;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.mapping.concertMember.ConcertMember;
import org.rubatophil.www.api.domain.type.Address;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Donate> donates = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<ConcertMember> members = new ArrayList<>();

    @NotNull
    private String name;
    @NotNull
    private LocalDate birth;
    @NotNull
    private String phoneNumber;

    @Embedded
    @NotNull
    private Address address;

    private String profileImage;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    public Member(String name, LocalDate birth, String phoneNumber, Address address, String profileImage) {
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profileImage = profileImage;
    }

    public void addDonate(Donate donate) {
        this.donates.add(donate);
        donate.setMember(this);
    }

    public void addConcertMember(ConcertMember concertMember) {
        this.members.add(concertMember);
        concertMember.setMember(this);
    }

    // TODO: default image url PrePersist 생성
    @PrePersist
    public void PrePersist() {
        this.profileImage = this.profileImage == null ? "www.default.com" : this.profileImage;
    }
}
