package org.rubatophil.www.api.response.donate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class DonateResponse {
    private String name;
}
