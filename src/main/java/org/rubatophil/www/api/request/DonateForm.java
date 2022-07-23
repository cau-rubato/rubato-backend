package org.rubatophil.www.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class DonateForm {
    private List<Long> budgetIds;
    private String message;

    @NotNull
    private Long amount;

    @NotNull
    private Long memberId;

}