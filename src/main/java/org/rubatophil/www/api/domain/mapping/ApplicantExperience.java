package org.rubatophil.www.api.domain.mapping;

import lombok.*;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantExperience {

    @Id @GeneratedValue
    @Column(name = "applicant_experience_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Applicant applicant;

    @Embedded
    @NotNull
    private Experience experience;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public ApplicantExperience(Applicant applicant, Experience experience) {
        this.applicant = applicant;
        this.experience = experience;
    }
}
