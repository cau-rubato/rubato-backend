package org.rubatophil.www.api.domain.concert;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.Account;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.ConcertPamphlet;
import org.rubatophil.www.api.domain.mapping.concertMember.ClubConcertMember;
import org.rubatophil.www.api.domain.mapping.concertMember.ConcertMember;
import org.rubatophil.www.api.domain.mapping.concertMember.GuestConcertMember;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.member.GuestMember;
import org.rubatophil.www.api.domain.type.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
public class ConcertTest {

    @PersistenceContext
    private EntityManager em;

    Address concertHallAddress;
    Location concertHallLocation;
    RegularConcert regularConcert;
    Account account;
    Department SWdepartment;

    @BeforeEach
    void setUp() {
        this.concertHallAddress = Address.builder()
                .zipcode("00000")
                .state("서울특별시")
                .city("동작구")
                .town("흑석동")
                .fullAddress("서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호")
                .build();

        this.concertHallLocation = Location.builder()
                .name("중앙대학교 루바토 동아리방")
                .address(concertHallAddress)
                .build();

        this.regularConcert = RegularConcert.builder()
                .name("test regular concert")
                .date(LocalDateTime.now())
                .location(concertHallLocation)
                .posterUrl("www.poster.com")
                .episode(0)
                .build();

        this.account = Account.builder()
                .login_id("test_id")
                .login_pw("test_pw")
                .build();

        this.SWdepartment = Department.builder()
                .college("창의ICT공과대학")
                .school("소프트웨어학부")
                .build();

        em.persist(this.concertHallLocation);
        em.persist(this.regularConcert);
        em.persist(this.account);
        em.persist(this.SWdepartment);
    }

    @Test
    @DisplayName("Regular Concert Table의 applyStatus field에 PrePersist가 잘 적용되는지 테스트")
    public void applyStatusFieldPrePersistTest() throws Exception {

        //given
        //when
        //then
        assertEquals(this.regularConcert.getApplyStatus(), ApplyStatus.CLOSED);
    }

    @Test
    @DisplayName("Regular Concert Table에 한 개의 Club Concert Member가 잘 들어가고 조회되는지 테스트")
    public void addOneClubConcertMemberTest() throws Exception {

        //given
        ClubMember clubMember = ClubMember.builder()
                .account(this.account)
                .name("test member name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.concertHallAddress)
                .generation(34)
                .department(SWdepartment)
                .studentId("20180000")
                .build();

        ClubConcertMember clubConcertMember = ClubConcertMember.builder()
                .member(clubMember)
                .instrument(Instrument.VIOLIN)
                .concertRole(ConcertRole.GENERAL)
                .build();

        //when
        this.regularConcert.addConcertMember(clubConcertMember);

        List<ConcertMember> testConcertMemberList = new ArrayList<>();
        testConcertMemberList.add(clubConcertMember);

        //then
        assertArrayEquals(this.regularConcert.getConcertMembers().toArray(), testConcertMemberList.toArray());
        assertEquals(clubConcertMember.getConcert(), this.regularConcert);
    }

    @Test
    @DisplayName("Regular Concert Table에 한 개의 Guest Concert Member가 잘 들어가고 조회되는지 테스트")
    public void addOneGuestConcertMemberTest() throws Exception {

        //given
        GuestMember guestMember = GuestMember.builder()
                .account(this.account)
                .name("test guest name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.concertHallAddress)
                .build();

        GuestConcertMember guestConcertMember = GuestConcertMember.builder()
                .member(guestMember)
                .instrument(Instrument.VIOLIN)
                .part(Part.FIRST_VIOLIN)
                .concertRole(ConcertRole.GENERAL)
                .salary(100000)
                .build();

        //when
        this.regularConcert.addConcertMember(guestConcertMember);

        List<ConcertMember> testConcertMemberList = new ArrayList<>();
        testConcertMemberList.add(guestConcertMember);

        //then
        assertArrayEquals(this.regularConcert.getConcertMembers().toArray(), testConcertMemberList.toArray());
        assertEquals(guestConcertMember.getConcert(), this.regularConcert);
    }

    @Test
    @DisplayName("Regular Concert Table에 한 개의 Concert Pamphlet이 잘 들어가고 조회되는지 테스트")
    public void addOneConcertPamphletTest() throws Exception {

        //given
        ConcertPamphlet firstConcertPamphlet = ConcertPamphlet.builder()
                .pageNumber(1)
                .imageUrl("www.first.com")
                .build();

        //when
        this.regularConcert.addConcertPamphlet(firstConcertPamphlet);

        List<ConcertPamphlet> testConcertPamphletList = new ArrayList<>();
        testConcertPamphletList.add(firstConcertPamphlet);

        //then
        assertArrayEquals(this.regularConcert.getConcertPamphlets().toArray(), testConcertPamphletList.toArray());
        assertEquals(firstConcertPamphlet.getConcert(), this.regularConcert);
    }
}
