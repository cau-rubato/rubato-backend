package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.type.ApplyStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APPLY_CAMPAIGN")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyCampaign {

    @Id @GeneratedValue
    @Column(name = "apply_campaign_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private Integer generation;

    @NotNull
    private LocalDateTime openedAt;
    @NotNull
    private LocalDateTime closedAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ApplyStatus status;

    @OneToMany(mappedBy = "applyCampaign", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Apply> applies = new ArrayList<>();

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public ApplyCampaign(Integer generation, LocalDateTime openedAt, LocalDateTime closedAt, ApplyStatus status) {
        this.generation = generation;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.status = status;
    }

    public void addApply(Apply apply) {
        this.applies.add(apply);
        apply.setApplyCampaign(this);
    }

    @PrePersist
    public void PrePersist() {
        this.status = this.status == null ? ApplyStatus.OPENED : this.status;
    }
}
