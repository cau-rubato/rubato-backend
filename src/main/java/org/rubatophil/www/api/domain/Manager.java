package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.rubatophil.www.api.domain.type.ManagerType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "MANAGER")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {

    @Id @GeneratedValue
    @Column(name = "manager_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @Setter(AccessLevel.NONE)
    private ClubMember clubMember;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ManagerType managerType;

    @NotNull
    private LocalDate startedAt;
    private LocalDate resignedAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ManagerStatus status;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Manager(ManagerType managerType, LocalDate startedAt, ManagerStatus status) {
        this.managerType = managerType;
        this.startedAt = startedAt;
        this.status = status;
    }

    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
        clubMember.setManager(this);
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? ManagerStatus.ACTIVATED : this.status;
    }
}
