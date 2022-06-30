package org.rubatophil.www.api.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.type.ApplyStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLY_CAMPAIGN")
@Getter @Setter
public class ApplyCampaign {

    @Id @GeneratedValue
    @Column(name = "apply_campaign_id")
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

    @OneToOne(mappedBy = "applyCampaign")
    private Apply apply;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    @PrePersist
    public void PrePersist() {
        this.status = this.status == null ? ApplyStatus.OPENED : this.status;
    }
}
