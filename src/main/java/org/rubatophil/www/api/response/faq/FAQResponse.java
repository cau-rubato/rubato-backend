package org.rubatophil.www.api.response.faq;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQResponse {
    private Long id;
    private String question;
    private String answer;

    @Builder
    public FAQResponse(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
}
