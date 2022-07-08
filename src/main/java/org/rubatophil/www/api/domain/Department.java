package org.rubatophil.www.api.domain;

import lombok.*;
import org.rubatophil.www.api.domain.member.Applicant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id @GeneratedValue
    @Column(name = "department_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Applicant> applicants = new ArrayList<>();

    private String college;
    private String school;
    private String department;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @Setter(AccessLevel.NONE)
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Department(String college, String school, String department) {
        this.college = college;
        this.school = school;
        this.department = department;
    }
}
