package org.rubatophil.www.api.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class NewFAQ {

    private String question;
    private String answer;

    @Builder
    public NewFAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
