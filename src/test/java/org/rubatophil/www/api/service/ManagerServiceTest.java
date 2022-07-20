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
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.rubatophil.www.api.domain.type.ManagerType;
import org.rubatophil.www.api.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(ManagerService.class)
public class ManagerServiceTest {

    @Autowired
    ManagerService managerService;

    @MockBean
    ManagerRepository managerRepository;

    List<Manager> managers;
    Manager manager;
    ClubMember clubMember;
    Department department;

    @BeforeEach
    void setUp() {

        this.department = Department.builder()
                .college("test college")
                .build();

        this.manager = Manager.builder()
                .managerType(ManagerType.PRESIDENT)
                .startedAt(LocalDate.of(2022, 1, 1))
                .build();
        this.clubMember = ClubMember.builder()
                .name("president")
                .birth(LocalDate.of(1999,1,1))
                .phoneNumber("010-0000-0000")
                .address(Address.builder()
                        .fullAddress("full address")
                        .build())
                .generation(34)
                .studentId("20180000")
                .build();
        this.clubMember.addMemberInstrument(MemberInstrument.builder()
                .instrument(Instrument.VIOLIN)
                .build()
        );
        this.manager.setClubMember(this.clubMember);
        this.department.addClubMember(this.clubMember);
    }

    @Test
    @DisplayName("getAllCurrentManagers")
    public void getAllCurrentManagersTest() {

        //given
        this.managers = new ArrayList<>();
        this.managers.add(this.manager);

        //when
        when(this.managerRepository.findAllByStatus(ManagerStatus.ACTIVATED)).thenReturn(this.managers);

        //then
        assertEquals(this.managers, this.managerService.getAllCurrentManagers());
    }

    @Test
    @DisplayName("addNewManager")
    public void addNewManagerTest() {

        //given
        //when
        this.managerService.addNewManager(this.manager);

        //then
        verify(this.managerRepository).save(this.manager);
    }
}
