package org.rubatophil.www.api.domain.mapping;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.Experience;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICANT_EXPERIENCE")
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

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
