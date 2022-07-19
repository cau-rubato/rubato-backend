package org.rubatophil.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.request.member.NewClubMember;
import org.rubatophil.www.api.response.member.ClubMemberResponse;
import org.rubatophil.www.api.service.member.ClubMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubMemberController {

    private final ClubMemberService clubMemberService;

    @GetMapping("v1/clubmembers")
    public List<ClubMemberResponse> getClubMemberInfo() {
        List<ClubMemberResponse> clubMemberList = new ArrayList<>();

        List<ClubMember> clubMemberResult = this.clubMemberService.getAllClubMembers();

        for (ClubMember result : clubMemberResult) {

            List<String> instrumentList = new ArrayList<>();

            for (MemberInstrument memberInstrument : result.getMemberInstruments()) {
                instrumentList.add(memberInstrument.getInstrument().toString());
            }

            String memberDepartment = result.getDepartment().getDepartment();
            if (memberDepartment == null) {
                memberDepartment = result.getDepartment().getSchool();
                if (memberDepartment == null) {
                    memberDepartment = result.getDepartment().getCollege();
                }
            }

            clubMemberList.add(ClubMemberResponse.builder()
                    .id(result.getId())
                    .name(result.getName())
                    .generation(result.getGeneration())
                    .instruments(instrumentList)
                    .department(memberDepartment)
                    .studentId(result.getStudentId())
                    .build()
            );
        }

        return clubMemberList;
    }

    @PostMapping("v1/clubmembers")
    public void postClubMemberInfo(@Valid @RequestBody NewClubMember newClubMember) {
        String[] births = newClubMember.getBirth().split("\\.");

        ClubMember clubMember = ClubMember.builder()
                .name(newClubMember.getName())
                .birth(LocalDate.of(Integer.parseInt(births[0]), Integer.parseInt(births[1]), Integer.parseInt(births[2])))
                .phoneNumber(newClubMember.getPhoneNumber())
                .address(Address.builder()
                        .zipcode(newClubMember.getZipcode())
                        .state(newClubMember.getState())
                        .city(newClubMember.getCity())
                        .town(newClubMember.getTown())
                        .fullAddress(newClubMember.getFullAddress())
                        .build())
                .generation(newClubMember.getGeneration())
                .studentId(newClubMember.getStudentId())
                .build();

        List<MemberInstrument> memberInstruments = new ArrayList<>();
        for (String instrument : newClubMember.getInstruments()) {
            MemberInstrument memberInstrument = MemberInstrument.builder()
                    .instrument(Instrument.valueOf(instrument.toUpperCase()))
                    .build();
            clubMember.addMemberInstrument(memberInstrument);
            memberInstruments.add(memberInstrument);
        }

        Department department = Department.builder()
                .college(newClubMember.getCollege())
                .school(newClubMember.getSchool())
                .department(newClubMember.getDepartment())
                .build();

        department.addClubMember(clubMember);

        clubMemberService.addNewClubMember(clubMember, department, memberInstruments);
    }

}
