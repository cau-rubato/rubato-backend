package org.rubatophil.www.api.response.apply;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ApplyStatusResponse {
    private String status;
}
