package org.rubatophil.www.api.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "FAQ")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQ {

    @Id @GeneratedValue
    @Column(name = "faq_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String question;

    @NotNull
    private String answer;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Builder
    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
