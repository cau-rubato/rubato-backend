package org.rubatophil.www.api.domain.mapping;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.domain.Donate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DONATE_BUDGET")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DonateBudget {

    @Id @GeneratedValue
    @Column(name = "donate_budget_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donate_id")
    private Donate donate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
}
