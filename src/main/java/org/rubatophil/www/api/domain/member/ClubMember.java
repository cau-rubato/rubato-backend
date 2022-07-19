package org.rubatophil.www.api.domain.member;

import lombok.*;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.type.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CLUB_MEMBER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMember extends Member {

    private Integer generation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "clubMember", cascade = CascadeType.ALL)
    private Manager manager;

    @NotNull
    private String studentId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<MemberInstrument> memberInstruments = new ArrayList<>();

    @Builder
    public ClubMember(String name, LocalDate birth, String phoneNumber, Address address, String profileImage, Integer generation, String studentId) {
        super(name, birth, phoneNumber, address, profileImage);
        this.generation = generation;
        this.studentId = studentId;
    }

    public void addMemberInstrument(MemberInstrument memberInstrument) {
        this.memberInstruments.add(memberInstrument);
        memberInstrument.setMember(this);
    }
}
