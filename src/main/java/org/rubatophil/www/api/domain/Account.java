package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.domain.type.AccountStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
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
    private Member member;

    private String login_id;
    private String login_pw;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Account(String login_id, String login_pw) {
        this.login_id = login_id;
        this.login_pw = login_pw;
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? AccountStatus.ACTIVATED : this.status;
    }
}
