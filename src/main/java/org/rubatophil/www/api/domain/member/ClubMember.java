package org.rubatophil.www.api.domain.member;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("CLUB_MEMBER")
@Getter @Setter
public class ClubMember extends Member {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @NotNull
    private String studentId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @NotNull
    private List<MemberInstrument> memberInstruments;
}
