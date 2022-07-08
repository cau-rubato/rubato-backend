package org.rubatophil.www.api.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.Account;
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
public class SponsorTest {

    @PersistenceContext
    private EntityManager em;

    Account account;
    Address address;
    Sponsor sponsor;

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

        this.sponsor = Sponsor.builder()
                .account(this.account)
                .name("test sponsor name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(this.address)
                .build();

        em.persist(this.account);
        em.persist(this.sponsor);
    }

    @Test
    @DisplayName("Sponsor Builder가 잘 작동하는지 테스트")
    public void sponsorBuilderTest() throws Exception {
        //given
        //when
        //then
        Sponsor emfindSponsor = em.find(Sponsor.class, this.sponsor.getId());

        assertEquals(emfindSponsor.getAccount(), this.account);
        assertEquals(emfindSponsor.getName(), "test sponsor name");
        assertEquals(emfindSponsor.getBirth(), LocalDate.of(1999, 01, 01));
        assertEquals(emfindSponsor.getPhoneNumber(), "01000000000");
        assertEquals(emfindSponsor.getAddress(), this.address);
    }
}
