package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.member.Applicant;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.DocumentStatus;
import org.rubatophil.www.api.domain.type.Instrument;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
class ApplyTest {

    @PersistenceContext
    private EntityManager em;

    Apply apply;

    @BeforeEach
    void setUp() {
        this.apply = Apply.builder()
                .preferredInstrument(Instrument.VIOLIN)
                .introduction("test introduction")
                .documentStatus(DocumentStatus.TEMPORARY)
                .build();

        em.persist(this.apply);
    }

    @Test
    @DisplayName("Apply Table의 isApproved field에 PrePersist가 잘 적용되는지 테스트")
    public void isApprovedPrePersistTest() throws Exception {

        //given
        //when
        //then
        Apply emfindApply = em.find(Apply.class, this.apply.getId());

        assertEquals(Boolean.FALSE, emfindApply.getIsApproved());
    }

    @Test
    @DisplayName("Apply Builder가 잘 작동하는지 테스트")
    public void applyBuilderTest() throws Exception {

        //given
        //when
        //then
        Apply emfindApply = em.find(Apply.class, this.apply.getId());

        assertEquals(Instrument.VIOLIN, emfindApply.getPreferredInstrument());
        assertEquals("test introduction", emfindApply.getIntroduction());
        assertEquals(DocumentStatus.TEMPORARY, emfindApply.getDocumentStatus());
    }

    @Test
    @DisplayName("Apply Table에 한 개의 Applicant가 잘 들어가고 조회되는지 테스트")
    public void addOneApplicantTest() throws Exception {

        //given
        Address address = Address.builder()
                .zipcode("00000")
                .state("서울특별시")
                .city("동작구")
                .town("흑석동")
                .fullAddress("서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호")
                .build();

        Applicant applicant = Applicant.builder()
                .name("test applicant name")
                .birth(LocalDate.of(1999, 1, 1))
                .phoneNumber("01000000000")
                .address(address)
                .studentId("20180000")
                .build();

        em.persist(applicant);

        //when
        this.apply.setApplicant(applicant);

        //then
        Apply emfindApply = em.find(Apply.class, this.apply.getId());
        Applicant emfindApplicant = em.find(Applicant.class, applicant.getId());

        assertEquals(this.apply.getApplicant(), emfindApply.getApplicant());
        assertEquals(emfindApply, emfindApplicant.getApply());

    }
}