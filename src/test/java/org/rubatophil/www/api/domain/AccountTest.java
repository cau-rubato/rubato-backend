package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.member.GuestMember;
import org.rubatophil.www.api.domain.member.Sponsor;
import org.rubatophil.www.api.domain.type.AccountStatus;
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
public class AccountTest {

    @PersistenceContext
    private EntityManager em;

    Account account;
    Address address;

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

        em.persist(this.account);
    }

    @Test
    @DisplayName("Account Table의 status field에 PrePersist가 잘 적용되는지 테스트")
    public void statusPrePersistTest() throws Exception {

        //given
        //when
        //then
        Account emfindAccount = em.find(Account.class, this.account.getId());

        assertEquals(emfindAccount.getStatus(), AccountStatus.ACTIVATED);
    }

    @Test
    @DisplayName("Account Builder가 잘 작동하는지 테스트")
    public void accountBuilderTest() throws Exception {

        //given
        //when
        //then
        Account emfindAccount = em.find(Account.class, this.account.getId());

        assertEquals(emfindAccount.getLoginId(), "test_id");
        assertEquals(emfindAccount.getLoginPw(), "test_pw");
    }

    @Test
    @DisplayName("Account Table에 한 개의 ClubMember가 잘 들어가고 조회되는지 테스트")
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
        this.account.setMember(clubMember);

        //then
        Account emfindAccount = em.find(Account.class, this.account.getId());
        ClubMember emfindClubMember = em.find(ClubMember.class, clubMember.getId());

        assertEquals(this.account.getMember(), emfindAccount.getMember());
        assertEquals(emfindAccount, emfindClubMember.getAccount());
    }

    @Test
    @DisplayName("Account Table에 한 개의 GuestMember가 잘 들어가고 조회되는지 테스트")
    public void addOneGuestMember() throws Exception {

        //given
        GuestMember guestMember = GuestMember.builder()
                .name("test guest member name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .build();

        em.persist(guestMember);

        //when
        this.account.setMember(guestMember);

        //then
        Account emfindAccount = em.find(Account.class, this.account.getId());
        GuestMember emfindGuestMember = em.find(GuestMember.class, guestMember.getId());

        assertEquals(this.account.getMember(), emfindAccount.getMember());
        assertEquals(emfindAccount, emfindGuestMember.getAccount());
    }

    @Test
    @DisplayName("Account Table에 한 개의 Sponsor가 잘 들어가고 조회되는지 테스트")
    public void addOneSponsorMember() throws Exception {

        //given
        Sponsor sponsor = Sponsor.builder()
                .name("test sponsor name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .build();

        em.persist(sponsor);

        //when
        this.account.setMember(sponsor);

        //then
        Account emfindAccount = em.find(Account.class, this.account.getId());
        Sponsor emfindSponsor = em.find(Sponsor.class, sponsor.getId());

        assertEquals(this.account.getMember(), emfindAccount.getMember());
        assertEquals(emfindAccount, emfindSponsor.getAccount());
    }
}