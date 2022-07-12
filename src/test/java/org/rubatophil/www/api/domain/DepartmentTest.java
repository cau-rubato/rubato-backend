package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.Address;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
public class DepartmentTest {

    @PersistenceContext
    private EntityManager em;

    Account account;
    Address address;
    Department department;

    @BeforeEach
    void setUp() {
        this.account = Account.builder()
                .loginId("test_id")
                .loginPw("test_pw")
                .build();

        em.persist(account);

        this.address = Address.builder()
                .zipcode("00000")
                .state("서울특별시")
                .city("동작구")
                .town("흑석동")
                .fullAddress("서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호")
                .build();

        this.department = Department.builder()
                .college("창의ICT공과대학")
                .school("소프트웨어학부")
                .build();

        em.persist(this.account);
        em.persist(this.department);
    }

    @Test
    @DisplayName("Department Builder가 잘 작동하는지 테스트")
    public void departmentBuilderTest() throws Exception {

        //given
        //when
        //then
        Department emfindDepartment = em.find(Department.class, this.department.getId());

        assertEquals("창의ICT공과대학", emfindDepartment.getCollege());
        assertEquals("소프트웨어학부", emfindDepartment.getSchool());
    }

    @Test
    @DisplayName("Department Table에 한 개의 Applicant가 잘 들어가고 조회되는지 테스트")
    public void addOneApplicantTest() throws Exception {

        //given
        Applicant applicant = Applicant.builder()
                .name("test applicant name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(address)
                .studentId("20180000")
                .build();

        em.persist(applicant);

        //when
        this.department.addApplicant(applicant);

        //then
        Department emfindDepartment = em.find(Department.class, this.department.getId());
        Applicant emfindApplicant = em.find(Applicant.class, applicant.getId());

        assertEquals(this.department.getApplicants().get(0), emfindDepartment.getApplicants().get(0));
        assertEquals(emfindDepartment, emfindApplicant.getDepartment());
    }

    @Test
    @DisplayName("Department Table에 한 개의 Club Member가 잘 들어가고 조회되는지 테스트")
    public void addOneClubMember() throws Exception {

        //given
        ClubMember clubMember = ClubMember.builder()
                .name("test club member name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .generation(34)
                .studentId("20180000")
                .build();

        em.persist(clubMember);

        //when
        this.department.addClubMember(clubMember);

        //then
        Department emfindDepartment = em.find(Department.class, this.department.getId());
        ClubMember emfindClubMember = em.find(ClubMember.class, clubMember.getId());

        assertEquals(this.department.getClubMembers().get(0), emfindDepartment.getClubMembers().get(0));
        assertEquals(emfindDepartment, emfindClubMember.getDepartment());
    }
}
