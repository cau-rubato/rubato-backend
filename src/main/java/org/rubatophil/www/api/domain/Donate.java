package org.rubatophil.www.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.rubatophil.www.api.domain.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DONATE")
@Getter @Setter
public class Donate {

    @Id @GeneratedValue
    @Column(name = "donate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @OneToMany(mappedBy = "donate", cascade = CascadeType.ALL)
    private List<DonateBudget> donateBudgets;

    private String message;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;
}
