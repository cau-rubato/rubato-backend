package org.rubatophil.www.api.domain.member;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.type.Experience;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Embedded
    private Experience experience;
}
