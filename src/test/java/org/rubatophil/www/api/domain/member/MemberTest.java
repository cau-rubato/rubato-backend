package org.rubatophil.www.api.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.Donate;
import org.rubatophil.www.api.domain.mapping.concertMember.ClubConcertMember;
import org.rubatophil.www.api.domain.mapping.concertMember.GuestConcertMember;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.ConcertRole;
import org.rubatophil.www.api.domain.type.Instrument;
import org.rubatophil.www.api.domain.type.Part;
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
public class MemberTest {

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
    @DisplayName("Club Member Table의 profileImage field에 PrePersist가 잘 적용되는지 테스트")
    public void profileImagePrePersistTest() throws Exception {

        //given
        //when
        //then
        assertEquals("www.default.com", this.clubMember.getProfileImage());
    }

    @Test
    @DisplayName("Club Member Table에 한 개의 Donate가 잘 들어가고 조회되는지 테스트")
    public void addOneDonateTest() throws Exception {

        //given
        Donate donate = Donate.builder()
                .message("test donate message")
                .build();

        em.persist(donate);

        //when
        clubMember.addDonate(donate);

        //then
        ClubMember emfindClubMember = em.find(ClubMember.class, clubMember.getId());
        Donate emfindDonate = em.find(Donate.class, donate.getId());

        assertEquals(this.clubMember.getDonates().get(0), emfindClubMember.getDonates().get(0));
        assertEquals(emfindClubMember, emfindDonate.getMember());
    }

    @Test
    @DisplayName("Club Member Table에 한 개의 Club Concert Member가 잘 들어가고 조회되는지 테스트")
    public void addOneClubConcertMemberTest() throws Exception {

        //given
        ClubConcertMember clubConcertMember = ClubConcertMember.builder()
                .instrument(Instrument.VIOLIN)
                .concertRole(ConcertRole.GENERAL)
                .build();

        em.persist(clubConcertMember);

        //when
        this.clubMember.addConcertMember(clubConcertMember);

        //then
        ClubMember emfindClubMember = em.find(ClubMember.class, this.clubMember.getId());
        ClubConcertMember emfindClubConcertMember = em.find(ClubConcertMember.class, clubConcertMember.getId());

        assertEquals(this.clubMember.getMembers().get(0), emfindClubMember.getMembers().get(0));
        assertEquals(emfindClubMember, emfindClubConcertMember.getMember());
    }

    @Test
    @DisplayName("Guest Member Table에 한 개의 Guest Concert Member가 잘 들어가고 조회되는지 테스트")
    public void addOneGuestConcertMemberTest() throws Exception {

        //given
        GuestMember guestMember = GuestMember.builder()
                .name("test guest name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .build();

        em.persist(guestMember);

        GuestConcertMember guestConcertMember = GuestConcertMember.builder()
                .instrument(Instrument.VIOLIN)
                .part(Part.FIRST_VIOLIN)
                .concertRole(ConcertRole.GENERAL)
                .salary(100000)
                .build();

        em.persist(guestConcertMember);

        //when
        guestMember.addConcertMember(guestConcertMember);

        //then
        GuestMember emfindGuestMember = em.find(GuestMember.class, guestMember.getId());
        GuestConcertMember emfindGuestConcertMember = em.find(GuestConcertMember.class, guestConcertMember.getId());

        assertEquals(guestMember.getMembers().get(0), emfindGuestMember.getMembers().get(0));
        assertEquals(emfindGuestMember, emfindGuestConcertMember.getMember());
    }
}
