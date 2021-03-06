package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.rubatophil.www.api.domain.member.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DONATE")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Donate {

    @Id @GeneratedValue
    @Column(name = "donate_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "donate", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<DonateBudget> donateBudgets = new ArrayList<>();

    private String message;
    @NotNull
    private Long amount;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Donate(String message, Long amount) {
        this.message = message;
        this.amount = amount;
    }

    public void addDonateBudget(DonateBudget donateBudget) {
        this.donateBudgets.add(donateBudget);
        donateBudget.setDonate(this);
    }
}
