package org.rubatophil.www.api.domain.member;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.Apply;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("APPLICANT")
@Getter @Setter
public class Applicant extends Member {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @NotNull
    private String studentId;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    private Apply apply;

    @OneToMany(mappedBy = "applicant")
    private List<ApplicantExperience> applicantExperiences = new ArrayList<>();
}
