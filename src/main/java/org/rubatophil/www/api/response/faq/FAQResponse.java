package org.rubatophil.www.api.response.faq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class FAQResponse {
    private Long id;
    private String question;
    private String answer;
}
