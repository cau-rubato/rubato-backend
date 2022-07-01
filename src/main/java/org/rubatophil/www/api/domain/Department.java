package org.rubatophil.www.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.member.Applicant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Getter @Setter
public class Department {

    @Id @GeneratedValue
    @Column(name = "department_id")
    private Long id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @NotNull
    private List<Applicant> applicants = new ArrayList<>();

    @NotNull
    private String college;
    @NotNull
    private String school;
    private String department;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
