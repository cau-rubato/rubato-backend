package org.rubatophil.www.api.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BUDGET")
@Getter @Setter
public class Budget {

    @Id @GeneratedValue
    @Column(name = "budget_id")
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Donate donate;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;
}
