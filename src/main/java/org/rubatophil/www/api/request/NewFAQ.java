package org.rubatophil.www.api.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class NewFAQ {

    @NotNull
    private String question;
    @NotNull
    private String answer;

    @Builder
    public NewFAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
