package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.response.member.MemberResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MemberController {

    @GetMapping("/members")
    public List<MemberResponse> searchMemberList(@RequestParam(value = "member_type") String memberType) {
        List<MemberResponse> memberResponses = new ArrayList<>();

        MemberResponse member = MemberResponse.builder()
                .name("백승윤")
                .generation(33)
                .department("전자전기공학부")
                .instrument("Vc.")
                .studentId("17")
                .build();

        memberResponses.add(member);

        return memberResponses;
    }

}
