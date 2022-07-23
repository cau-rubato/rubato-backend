package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id @GeneratedValue
    @Column(name = "department_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Applicant> applicants = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<ClubMember> clubMembers = new ArrayList<>();

    @NotNull
    private String college;
    private String school;
    private String department;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @Setter(AccessLevel.NONE)
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Department(String college, String school, String department) {
        this.college = college;
        this.school = school;
        this.department = department;
    }

    public void addApplicant(Applicant applicant) {
        this.applicants.add(applicant);
        applicant.setDepartment(this);
    }

    public void addClubMember(ClubMember clubMember) {
        this.clubMembers.add(clubMember);
        clubMember.setDepartment(this);
    }
}
