package org.rubatophil.www.api.domain.mapping;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.Experience;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "APPLICATION_EXPERIENCE")
@Getter @Setter
public class ApplicantExperience {

    @Id @GeneratedValue
    @Column(name = "applicant_experience_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Applicant applicant;

    @Embedded
    private Experience experience;
}
