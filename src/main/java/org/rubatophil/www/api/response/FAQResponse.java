package org.rubatophil.www.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class FAQResponse {
    private Long id;
    private String question;
    private String answer;
}
