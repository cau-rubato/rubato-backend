package org.rubatophil.www.api.domain.member;

import lombok.*;
import org.rubatophil.www.api.domain.Account;
import org.rubatophil.www.api.domain.Apply;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.type.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("APPLICANT")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant extends Member {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @NotNull
    private String studentId;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Apply apply;

    @OneToMany(mappedBy = "applicant")
    @Setter(AccessLevel.NONE)
    private List<ApplicantExperience> applicantExperiences = new ArrayList<>();

    @Builder
    public Applicant(Account account, String name, LocalDate birth, String phoneNumber, Address address, Department department, String studentId) {
        super(account, name, birth, phoneNumber, address);
        this.department = department;
        this.studentId = studentId;
    }

    public void addApplicantExperience(ApplicantExperience applicantExperience) {
        this.applicantExperiences.add(applicantExperience);
        applicantExperience.setApplicant(this);
    }
}
