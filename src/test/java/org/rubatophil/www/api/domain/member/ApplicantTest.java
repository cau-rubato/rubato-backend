package org.rubatophil.www.api.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.Account;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.ApplicantExperience;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.Experience;
import org.rubatophil.www.api.domain.type.Instrument;
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
public class ApplicantTest {

    @PersistenceContext
    private EntityManager em;

    Account account;
    Address address;
    Department swDepartment;
    Applicant applicant;

    @BeforeEach
    void setUp() {
        this.account = Account.builder()
                .login_id("test_id")
                .login_pw("test_pw")
                .build();

        this.address = Address.builder()
                .zipcode("00000")
                .state("서울특별시")
                .city("동작구")
                .town("흑석동")
                .fullAddress("서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호")
                .build();

        this.swDepartment= Department.builder()
                .college("창의ICT공과대학")
                .school("소프트웨어학부")
                .build();

        this.applicant = Applicant.builder()
                .account(this.account)
                .name("test applicant name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .department(this.swDepartment)
                .studentId("20180000")
                .build();

        em.persist(this.account);
        em.persist(this.swDepartment);
        em.persist(this.applicant);
    }

    @Test
    @DisplayName("Applicant Builder가 잘 작동하는지 테스트")
    public void applicantBuilderTest() throws Exception {

        //given
        //when
        //then
        Applicant emfindApplicant = em.find(Applicant.class, this.applicant.getId());

        assertEquals(emfindApplicant.getAccount(), this.account);
        assertEquals(emfindApplicant.getName(), "test applicant name");
        assertEquals(emfindApplicant.getBirth(), LocalDate.of(1999, 01, 01));
        assertEquals(emfindApplicant.getPhoneNumber(), "01000000000");
        assertEquals(emfindApplicant.getAddress(), this.address);
        assertEquals(emfindApplicant.getDepartment(), this.swDepartment);
        assertEquals(emfindApplicant.getStudentId(), "20180000");
    }

    @Test
    @DisplayName("Applicant Table에 한 개의 Applicant Experience가 잘 들어가고 조회되는지 테스트")
    public void addOneApplicantExperienceTest() throws Exception {

        //given
        Experience experience = Experience.builder()
                .instrument(Instrument.VIOLIN)
                .experienceYear(10)
                .build();

        ApplicantExperience applicantExperience = ApplicantExperience.builder()
                .experience(experience)
                .build();

        em.persist(applicantExperience);

        //when
        this.applicant.addApplicantExperience(applicantExperience);

        //then
        Applicant emfindApplicant = em.find(Applicant.class, this.applicant.getId());
        ApplicantExperience emfindApplicantExperience = em.find(ApplicantExperience.class, applicantExperience.getId());

        assertEquals(this.applicant.getApplicantExperiences().get(0), emfindApplicant.getApplicantExperiences().get(0));
        assertEquals(emfindApplicantExperience.getApplicant(), emfindApplicant);
    }
}