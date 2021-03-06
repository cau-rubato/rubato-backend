package org.rubatophil.www.api.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    Address address;
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

        this.clubMember = ClubMember.builder()
                .name("test club member name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .generation(34)
                .studentId("20180000")
                .build();

        em.persist(this.clubMember);
    }

    @Test
    @DisplayName("Club Member Builder가 잘 작동하는지 테스트")
    public void clubMemberBuilderTest() throws Exception {

        //given
        //when
        //then
        ClubMember emfindClubMember = em.find(ClubMember.class, this.clubMember.getId());

        assertEquals("test club member name", emfindClubMember.getName());
        assertEquals(LocalDate.of(1999, 01, 01), emfindClubMember.getBirth());
        assertEquals("01000000000", emfindClubMember.getPhoneNumber());
        assertEquals(this.address, emfindClubMember.getAddress());
        assertEquals(34, emfindClubMember.getGeneration());
        assertEquals("20180000", emfindClubMember.getStudentId());
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
        assertEquals(emfindClubMember, emfindMemberInstrument.getMember());
    }
}
