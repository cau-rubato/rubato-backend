package org.rubatophil.www.api.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewManager {

    @NotNull
    private String managerType;
    @NotNull
    private Long memberId;
    @NotNull
    private String startedAt;
    private String status;

    @Builder
    public NewManager(String managerType, Long memberId, String startedAt, String status) {
        this.managerType = managerType;
        this.memberId = memberId;
        this.startedAt = startedAt;
        this.status = status;
    }
}
