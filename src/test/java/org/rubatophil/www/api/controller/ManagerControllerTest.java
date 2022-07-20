package org.rubatophil.www.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.type.ManagerType;
import org.rubatophil.www.api.service.ManagerService;
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

@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ManagerService managerService;
    List<Manager> managers;
    Manager president;
    ClubMember memberPresident;
    Manager vicePresident;
    ClubMember memberVicePresident;
    Department department;

    @BeforeEach
    void setUp() {

        this.department = Department.builder()
                .college("test college")
                .build();

        this.president = Manager.builder()
                .managerType(ManagerType.PRESIDENT)
                .startedAt(LocalDate.of(2022, 1, 1))
                .build();
        this.memberPresident = ClubMember.builder()
                .name("president")
                .birth(LocalDate.of(1999,1,1))
                .phoneNumber("010-0000-0000")
                .address(Address.builder()
                        .fullAddress("full address")
                        .build())
                .generation(34)
                .studentId("20180000")
                .build();
        this.memberPresident.addMemberInstrument(MemberInstrument.builder()
                .instrument(Instrument.VIOLIN)
                .build()
        );
        this.president.setClubMember(this.memberPresident);
        this.department.addClubMember(this.memberPresident);

        this.vicePresident = Manager.builder()
                .managerType(ManagerType.VICE_PRESIDENT)
                .startedAt(LocalDate.of(2022, 1, 1))
                .build();
        this.memberVicePresident = ClubMember.builder()
                .name("vice president")
                .birth(LocalDate.of(1999,1,1))
                .phoneNumber("010-0000-0000")
                .address(Address.builder()
                        .fullAddress("full address")
                        .build())
                .generation(34)
                .studentId("20180000")
                .build();
        this.memberVicePresident.addMemberInstrument(MemberInstrument.builder()
                .instrument(Instrument.VIOLIN)
                .build()
        );
        this.vicePresident.setClubMember(this.memberVicePresident);
        this.department.addClubMember(this.memberVicePresident);
    }

    @Test
    @DisplayName("getCurrentManagerInfo")
    public void getCurrentManagerInfoTest() throws Exception {

        //given
        String url = "/v1/managers";
        String expectedJson = "[{\"managerType\":\"PRESIDENT\",\"name\":\"president\",\"profileImage\":null,\"instrument\":[\"VIOLIN\"],\"generation\":34,\"department\":\"test college\",\"admissionYear\":\"18\"},{\"managerType\":\"VICE_PRESIDENT\",\"name\":\"vice president\",\"profileImage\":null,\"instrument\":[\"VIOLIN\"],\"generation\":34,\"department\":\"test college\",\"admissionYear\":\"18\"}]";

        this.managers = new ArrayList<>();
        this.managers.add(this.president);
        this.managers.add(this.vicePresident);

        //when
        when(this.managerService.getAllCurrentManagers()).thenReturn(this.managers);

        ResultActions mvcResult = this.mockMvc.perform(get(url));

        //then
        mvcResult.andExpect(status().isOk())
                .andExpect(content().string(expectedJson));
    }

    @Test
    @DisplayName("postManagerInfo")
    public void postManagerInfoTest() throws Exception {

        //given
        String url = "/v1/managers";
        String newManager = "{\"managerType\":\"SECRETARY\",\"memberId\":1,\"startedAt\":\"2022.01.01\"}";

        ClubMember memberSecretary = ClubMember.builder()
                .name("secretary")
                .birth(LocalDate.of(1999,1,1))
                .phoneNumber("010-0000-0000")
                .address(Address.builder()
                        .fullAddress("full address")
                        .build())
                .generation(34)
                .studentId("20180000")
                .build();
        this.department.addClubMember(memberSecretary);

        ReflectionTestUtils.setField(memberSecretary, "id", 1L);

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newManager)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isOk());
    }

    @Test
    @DisplayName("[postManagerInfo] 하나의 request field가 null")
    public void postManagerInfoNullTest() throws Exception {

        //given
        String url = "/v1/managers";
        String newManager = "{\"memberId\":1,\"startedAt\":\"2022.01.01\"}";

        ClubMember memberSecretary = ClubMember.builder()
                .name("secretary")
                .birth(LocalDate.of(1999,1,1))
                .phoneNumber("010-0000-0000")
                .address(Address.builder()
                        .fullAddress("full address")
                        .build())
                .generation(34)
                .studentId("20180000")
                .build();
        this.department.addClubMember(memberSecretary);

        ReflectionTestUtils.setField(memberSecretary, "id", 1L);

        //when
        ResultActions mvcResult = this.mockMvc.perform(post(url)
                .content(newManager)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        mvcResult.andExpect(status().isBadRequest());
    }
}
