package org.rubatophil.www.api.response.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class ConcertSummaryResponse {

    private Integer episode;
    private String conductor;
    private String collaborator;
    private String concertmaster;
    private String posterUrl;
    private LocalDateTime date;
    private String location;
}
