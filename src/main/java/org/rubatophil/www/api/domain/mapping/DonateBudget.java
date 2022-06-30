package org.rubatophil.www.api.domain.mapping;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.Budget;
import org.rubatophil.www.api.domain.Donate;

import javax.persistence.*;

@Entity
@Table(name = "DONATE_BUDGET")
@Getter @Setter
public class DonateBudget {

    @Id @GeneratedValue
    @Column(name = "donate_budget_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donate_id")
    @NotNull
    private Donate donate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    @NotNull
    private Budget budget;
}
