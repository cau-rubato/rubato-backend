package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.DocumentStatus;
import org.rubatophil.www.api.domain.type.Instrument;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLY")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {

    @Id @GeneratedValue
    @Column(name = "apply_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @Setter(AccessLevel.NONE)
    private Applicant applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_campaign_id")
    private ApplyCampaign applyCampaign;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Instrument preferredInstrument;
    @NotNull
    private String introduction;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DocumentStatus documentStatus;

    private Boolean isApproved;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Apply(Instrument preferredInstrument, String introduction, DocumentStatus documentStatus) {
        this.preferredInstrument = preferredInstrument;
        this.introduction = introduction;
        this.documentStatus = documentStatus;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
        applicant.setApply(this);
    }

    @PrePersist
    public void PrePersist() {
        this.isApproved = this.isApproved == null ? Boolean.FALSE : this.isApproved;
    }
}
