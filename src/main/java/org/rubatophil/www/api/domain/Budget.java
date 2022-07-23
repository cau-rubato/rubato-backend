package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.mapping.DonateBudget;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BUDGET")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Budget {

    @Id @GeneratedValue
    @Column(name = "budget_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<DonateBudget> donateBudgets = new ArrayList<>();

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public Budget(String name) {
        this.name = name;
    }

    public void addDonateBudget(DonateBudget donateBudget) {
        this.donateBudgets.add(donateBudget);
        donateBudget.setBudget(this);
    }
}
