package org.rubatophil.www.api.service.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.repository.DepartmentRepository;
import org.rubatophil.www.api.repository.mapping.MemberInstrumentRepository;
import org.rubatophil.www.api.repository.member.ClubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(ClubMemberService.class)
public class ClubMemberServiceTest {

    @Autowired
    ClubMemberService clubMemberService;

    @MockBean
    ClubMemberRepository clubMemberRepository;
    @MockBean
    MemberInstrumentRepository memberInstrumentRepository;
    @MockBean
    DepartmentRepository departmentRepository;

    List<ClubMember> clubMembers;

    Address address;
    Department department;
    ClubMember clubMember;

    @BeforeEach
    void setUp() {

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
    @DisplayName("getAllClubMembers")
    public void getAllClubMembersTest() {

        //given
        this.clubMembers = new ArrayList<>();
        this.clubMembers.add(this.clubMember);

        //when
        when(this.clubMemberRepository.findAll()).thenReturn(this.clubMembers);

        //then
        assertEquals(this.clubMembers, this.clubMemberService.getAllClubMembers());
    }

    @Test
    @DisplayName("addNewClubMember")
    public void addNewClubMemberTest() {

        //given
        //when
        when(this.departmentRepository.findById(this.department.getId())).thenReturn(Optional.ofNullable(this.department));

        this.clubMemberService.addNewClubMember(this.clubMember, this.department.getId(), this.clubMember.getMemberInstruments());

        //then
        verify(this.clubMemberRepository).save(this.clubMember);
    }
}
