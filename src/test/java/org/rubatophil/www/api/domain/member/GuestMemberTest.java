package org.rubatophil.www.api.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.Account;
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
public class GuestMemberTest {

    @PersistenceContext
    private EntityManager em;

    Account account;
    Address address;
    GuestMember guestMember;

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

        this.guestMember = GuestMember.builder()
                .name("test guest member name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .build();

        em.persist(this.account);
        em.persist(this.guestMember);
    }

    @Test
    @DisplayName("Guest Member Builder가 잘 작동하는지 테스트")
    public void guestMemberBuilderTest() throws Exception {

        //given
        //when
        //then
        GuestMember emfindGuestMember = em.find(GuestMember.class, this.guestMember.getId());

        assertEquals(emfindGuestMember.getName(), "test guest member name");
        assertEquals(emfindGuestMember.getBirth(), LocalDate.of(1999, 01, 01));
        assertEquals(emfindGuestMember.getPhoneNumber(), "01000000000");
        assertEquals(emfindGuestMember.getAddress(), this.address);
    }

    @Test
    @DisplayName("Guest Member Table에 한 개의 Member Instrument가 잘 들어가고 조회되는지 테스트")
    public void addOneMemberInstrument() throws Exception {

        //given
        MemberInstrument memberInstrument = MemberInstrument.builder()
                .instrument(Instrument.VIOLIN)
                .build();

        em.persist(memberInstrument);

        //when
        this.guestMember.addMemberInstrument(memberInstrument);

        //then
        GuestMember emfindGuestMember = em.find(GuestMember.class, this.guestMember.getId());
        MemberInstrument emfindMemberInstrument = em.find(MemberInstrument.class, memberInstrument.getId());

        assertEquals(this.guestMember.getMemberInstruments().get(0), emfindGuestMember.getMemberInstruments().get(0));
        assertEquals(emfindGuestMember, emfindMemberInstrument.getMember());
    }
}
