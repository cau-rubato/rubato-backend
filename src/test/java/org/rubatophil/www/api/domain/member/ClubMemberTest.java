package org.rubatophil.www.api.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.Account;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.type.Address;
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
public class ClubMemberTest {

    @PersistenceContext
    private EntityManager em;

    Account account;
    Address address;
    Department swDepartment;
    ClubMember clubMember;

    @BeforeEach
    void setUp() {
        this.account = Account.builder()
                .loginId("test_id")
                .loginPw("test_pw")
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

        this.clubMember = ClubMember.builder()
                .account(this.account)
                .name("test club member name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .generation(34)
                .department(this.swDepartment)
                .studentId("20180000")
                .build();

        em.persist(this.account);
        em.persist(this.swDepartment);
        em.persist(this.clubMember);
    }

    @Test
    @DisplayName("Club Member Builder가 잘 작동하는지 테스트")
    public void clubMemberBuilderTest() throws Exception {

        //given
        //when
        //then
        ClubMember emfindClubMember = em.find(ClubMember.class, this.clubMember.getId());

        assertEquals(emfindClubMember.getAccount(), this.account);
        assertEquals(emfindClubMember.getName(), "test club member name");
        assertEquals(emfindClubMember.getBirth(), LocalDate.of(1999, 01, 01));
        assertEquals(emfindClubMember.getPhoneNumber(), "01000000000");
        assertEquals(emfindClubMember.getAddress(), this.address);
        assertEquals(emfindClubMember.getGeneration(), 34);
        assertEquals(emfindClubMember.getDepartment(), this.swDepartment);
        assertEquals(emfindClubMember.getStudentId(), "20180000");
    }

    @Test
    @DisplayName("Club Member Table에 한 개의 Member Instrument가 잘 들어가고 조회되는지 테스트")
    public void addOneMemberInstrumentTest() throws Exception {

        //given
        MemberInstrument memberInstrument = MemberInstrument.builder()
                .instrument(Instrument.VIOLIN)
                .build();

        em.persist(memberInstrument);

        //when
        this.clubMember.addMemberInstrument(memberInstrument);

        //then
        ClubMember emfindClubMember = em.find(ClubMember.class, this.clubMember.getId());
        MemberInstrument emfindMemberInstrument = em.find(MemberInstrument.class, memberInstrument.getId());

        assertEquals(this.clubMember.getMemberInstruments().get(0), emfindClubMember.getMemberInstruments().get(0));
        assertEquals(emfindMemberInstrument.getMember(), emfindClubMember);
    }
}
