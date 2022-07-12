package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.domain.type.AccountStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @Setter(AccessLevel.NONE)
    private Member member;

    @NotNull
    private String loginId;
    @NotNull
    private String loginPw;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Account(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    public void setMember(Member member) {
        this.member = member;
        member.setAccount(this);
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? AccountStatus.ACTIVATED : this.status;
    }
}
