package org.rubatophil.www.api.response.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ConcertLatestResponse {

    private Integer episode;
    private String name;
    private String detail;
    private String posterUrl;
    private String profileUrl;

}
