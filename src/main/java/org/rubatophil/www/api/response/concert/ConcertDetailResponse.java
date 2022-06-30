package org.rubatophil.www.api.response.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
public class ConcertDetailResponse {

    private Integer episode;
    private String conductor;
    private String collaborator;
    private String concertmaster;
    private LocalDateTime date;
    private String location;

    private String program;

    private String posterUrl;
    private List<String> pamphletUrls;

    private List<ConcertPartVO> members = new ArrayList<>();

}
