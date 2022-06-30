package org.rubatophil.www.api.response.apply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class ApplyDurationResponse {
    private LocalDateTime start;
    private LocalDateTime end;
}
