package org.rubatophil.www.api.controller;

import org.rubatophil.www.api.response.MemberResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MemberController {

    @GetMapping("/members")
    public List<MemberResponse> searchMemberList(@RequestParam(value = "member_type") String member_type) {
        List<MemberResponse> memberResponseList = new ArrayList<MemberResponse>();

        return memberResponseList;
    }

}
