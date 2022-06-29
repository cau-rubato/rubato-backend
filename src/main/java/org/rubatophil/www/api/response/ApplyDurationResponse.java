package org.rubatophil.www.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class ApplyDurationResponse {
    private LocalDateTime start;
    private LocalDateTime end;
}
