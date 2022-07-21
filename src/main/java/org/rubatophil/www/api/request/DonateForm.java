package org.rubatophil.www.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DonateForm {

    private List<Long> budgetIds;
    private String message;
    private Long amount;
    private Long memberId;

}