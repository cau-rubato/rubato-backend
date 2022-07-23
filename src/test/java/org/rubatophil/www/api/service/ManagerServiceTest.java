package org.rubatophil.www.api.service;

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
import org.rubatophil.www.api.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(ManagerService.class)
public class ManagerServiceTest {

    @Autowired
    ManagerService managerService;

    @MockBean
    ManagerRepository managerRepository;
    Manager president;
    ClubMember memberPresident;
    Manager vicePresident;
    ClubMember memberVicePresident;
    Manager secretary;
    ClubMember memberSecretary;
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

        this.secretary = Manager.builder()
                .managerType(ManagerType.SECRETARY)
                .startedAt(LocalDate.of(2022, 1, 1))
                .build();
        this.memberSecretary = ClubMember.builder()
                .name("secretary")
                .birth(LocalDate.of(1999,1,1))
                .phoneNumber("010-0000-0000")
                .address(Address.builder()
                        .fullAddress("full address")
                        .build())
                .generation(34)
                .studentId("20180000")
                .build();
        this.memberSecretary.addMemberInstrument(MemberInstrument.builder()
                .instrument(Instrument.VIOLIN)
                .build()
        );
        this.secretary.setClubMember(this.memberSecretary);
        this.department.addClubMember(this.memberSecretary);
    }

    @Test
    @DisplayName("getPresident")
    public void getPresidentTest() {

        //given
        //when
        when(this.managerRepository.findByManagerType(ManagerType.PRESIDENT)).thenReturn(this.president);

        //then
        assertEquals(this.president, this.managerService.getPresident());
    }

    @Test
    @DisplayName("getVicePresident")
    public void getVicePresidentTest() {

        //given
        //when
        when(this.managerRepository.findByManagerType(ManagerType.VICE_PRESIDENT)).thenReturn(this.vicePresident);

        //then
        assertEquals(this.vicePresident, this.managerService.getVicePresident());
    }

    @Test
    @DisplayName("getSecretary")
    public void getSecretaryTest() {

        //given
        //when
        when(this.managerRepository.findByManagerType(ManagerType.SECRETARY)).thenReturn(this.secretary);

        //then
        assertEquals(this.secretary, this.managerService.getSecretary());
    }

    @Test
    @DisplayName("addNewManager")
    public void addNewManagerTest() {

        //given
        //when
        this.managerService.addNewManager(this.president);

        //then
        verify(this.managerRepository).save(this.president);
    }
}
