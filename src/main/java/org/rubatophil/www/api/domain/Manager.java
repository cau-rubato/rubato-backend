package org.rubatophil.www.api.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.rubatophil.www.api.domain.type.ManagerType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MANAGER")
@Getter @Setter
public class Manager {

    @Id @GeneratedValue
    @Column(name = "manager_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private ClubMember clubMember;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ManagerType managerType;

    @NotNull
    private LocalDateTime startedAt;
    @NotNull
    private LocalDateTime resignedAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ManagerStatus status;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? ManagerStatus.ACTIVATED : this.status;
    }
}
