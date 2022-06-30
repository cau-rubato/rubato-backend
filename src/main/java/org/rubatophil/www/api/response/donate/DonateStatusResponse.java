package org.rubatophil.www.api.response.donate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class DonateStatusResponse {
    private String status;
}
