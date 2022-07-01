package org.rubatophil.www.api.domain;

import lombok.Getter;
import lombok.Setter;
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
public class Budget {

    @Id @GeneratedValue
    @Column(name = "budget_id")
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<DonateBudget> donateBudgets = new ArrayList<>();

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;
}
