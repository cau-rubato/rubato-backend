package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.response.concert.ConcertDetailResponse;
import org.rubatophil.www.api.response.concert.ConcertLatestResponse;
import org.rubatophil.www.api.response.concert.ConcertSummaryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ConcertController {

    @GetMapping("/concerts/latest")
    public ConcertLatestResponse latestConcert() {
        ConcertLatestResponse concertLatestResponse =
                ConcertLatestResponse.builder()
                        .episode(58)
                        .name("Resurrection")
                        .detail("루바토 제 58회 정기연주회였습니다")
                        .posterUrl("")
                        .profileUrl("")
                        .build();
        return concertLatestResponse;
    }

    @GetMapping("/concerts/{concert_id}")
    public ConcertDetailResponse detailConcertInfo(@PathVariable("concert_id") String concert_id) {
        ConcertDetailResponse concertDetailResponse =
                ConcertDetailResponse.builder()
                        .episode(58)
                        .conductor("강성우")
                        .collaborator("강승연")
                        .concertmaster("한동완")
                        .date(LocalDateTime.now())
                        .location("안양아트센터")
                        .program("드보르작 교향곡 8번")
                        .posterUrl("")
                        .pamphletUrls(new ArrayList<>())
                        .members(new ArrayList<>())
                        .build();

        return concertDetailResponse;
    }

    @GetMapping("/concerts")
    public List<ConcertSummaryResponse> concertList() {
        List<ConcertSummaryResponse> concertSummaryResponses =
            new ArrayList<>();

        ConcertSummaryResponse concert1 = ConcertSummaryResponse.builder()
                .episode(58)
                .conductor("강성우")
                .collaborator("강승연")
                .concertmaster("한동완")
                .posterUrl("")
                .date(LocalDateTime.now())
                .location("안양아트센터")
                .build();

        ConcertSummaryResponse concert2 = ConcertSummaryResponse.builder()
                .episode(59)
                .conductor("김다솔")
                .collaborator(null)
                .concertmaster("방수진")
                .posterUrl("")
                .date(LocalDateTime.now())
                .location("성남아트센터")
                .build();

        return concertSummaryResponses;
    }

}
