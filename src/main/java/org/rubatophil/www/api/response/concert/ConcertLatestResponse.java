package org.rubatophil.www.api.response.concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ConcertLatestResponse {

    private Integer episode;
    private String name;
    private String detail;
    private String posterUrl;
    private String profileUrl;

}
