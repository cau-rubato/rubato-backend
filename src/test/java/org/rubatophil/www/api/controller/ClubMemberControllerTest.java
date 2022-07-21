package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.service.member.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClubMemberController.class)
class ClubMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClubMemberService clubMemberService;

    List<ClubMember> clubMembers;

    Address address;
    Department department;
    ClubMember clubMember;

    @BeforeEach
    void setup() {

        this.address = Address.builder()
                .zipcode("00000")
                .state("서울특별시")
                .city("동작구")
                .town("흑석동")
                .fullAddress("서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호")
                .build();

        this.department = Department.builder()
                .college("창의ICT공과대학")
                .school("School of Computer Science and Engineering")
                .build();

        ReflectionTestUtils.setField(this.department, "id", 1L);

        this.clubMember = ClubMember.builder()
                .name("test club member name")
                .birth(LocalDate.of(1999, 1, 1))
                .phoneNumber("01000000000")
                .address(this.address)
                .generation(34)
                .studentId("20180000")
                .build();

        this.clubMember.addMemberInstrument(MemberInstrument.builder()
                .instrument(Instrument.VIOLIN)
                .build()
        );
        this.clubMember.addMemberInstrument(MemberInstrument.builder()
                .instrument(Instrument.VIOLA)
                .build()
        );
        this.department.addClubMember(this.clubMember);
    }

    @Test
    @DisplayName("getClubMemberInfo")
    public void getClubMemberInfoTest() throws Exception {

        //given
        String url = "/v1/clubmembers";
        String expectedJson = "[{\"id\":null,\"name\":\"test club member name\",\"generation\":34,\"instruments\":[\"VIOLIN\",\"VIOLA\"],\"department\":\"School of Computer Science and Engineering\",\"studentId\":\"20180000\"}]";

        this.clubMembers = new ArrayList<>();
        this.clubMembers.add(this.clubMember);

        //when
        when(this.clubMemberService.getAllClubMembers()).thenReturn(this.clubMembers);

        ResultActions mvcResult = this.mockMvc.perform(get(url));

        //then
        mvcResult.andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

    @Test
    @DisplayName("postClubMemberInfo")
    public void postClubMemberInfoTest() throws Exception {

        //given
        String url = "/v1/clubmembers";
        String newClubMember = "{\"name\":\"test club member name\",\"birth\":\"1999.01.01\",\"phoneNumber\":\"010-0000-0000\",\"zipcode\":\"00000\",\"state\":\"서울특별시\",\"city\":\"동작구\",\"town\":\"흑석동\",\"fullAddress\":\"서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호\",\"generation\":34,\"instruments\":[\"VIOLIN\",\"VIOLA\"],\"departmentId\":\"1\",\"studentId\":\"20180000\"}";

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newClubMember)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isOk());
    }

    @Test
    @DisplayName("[postClubMemberInfo] 하나의 request field가 null")
    public void postClubMemberInfoNullTest() throws Exception {

        //given
        String url = "/v1/clubmembers";
        String newClubMember = "{\"birth\":\"1999.01.01\",\"phoneNumber\":\"010-0000-0000\",\"zipcode\":\"00000\",\"state\":\"서울특별시\",\"city\":\"동작구\",\"town\":\"흑석동\",\"fullAddress\":\"서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호\",\"generation\":34,\"instruments\":[\"VIOLIN\",\"VIOLA\"],\"departmentId\":\"1\",\"studentId\":\"20180000\"}";

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newClubMember)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isBadRequest());
    }
}