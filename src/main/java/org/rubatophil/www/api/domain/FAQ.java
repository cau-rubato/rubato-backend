package org.rubatophil.www.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "FAQ")
@Getter @Setter
public class FAQ {

    @Id @GeneratedValue
    @Column(name = "faq_id")
    private Long id;

    @NotNull
    private String question;
    @NotNull
    private String answer;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
