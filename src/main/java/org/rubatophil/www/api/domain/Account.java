package org.rubatophil.www.api.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.member.Member;
import org.rubatophil.www.api.domain.type.AccountStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT")
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @NotNull
    private String login_id;
    @NotNull
    private String login_pw;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountStatus status;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? AccountStatus.ACTIVATED : this.status;
    }
}
