package org.rubatophil.www.api.controller;

import net.bytebuddy.asm.Advice;
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
                new ConcertLatestResponse(58, "Resurrection", "루바토 제 58회 정기연주회였습니다", "", "");

        return concertLatestResponse;
    }

    @GetMapping("/concerts/{concert_id}")
    public ConcertDetailResponse detailConcertInfo(@PathVariable("concert_id") String concert_id) {
        ConcertDetailResponse concertDetailResponse =
                new ConcertDetailResponse(58, "강성우", "강승연", "한동완", LocalDateTime.now(), "안양", "드보르작8번","", new ArrayList<>(), new ArrayList<>());

        return concertDetailResponse;
    }

    @GetMapping("/concerts")
    public List<ConcertSummaryResponse> concertList() {
        List<ConcertSummaryResponse> concertSummaryResponses =
            new ArrayList<>();

        concertSummaryResponses.add(new ConcertSummaryResponse(58, "강성우", "강승연", "한동완", "", LocalDateTime.now(), "안양아트센터"));
        concertSummaryResponses.add(new ConcertSummaryResponse(59, "김다솔", null, "방수진", "", LocalDateTime.now(), "성남아트센터"));

        return concertSummaryResponses;
    }

}
