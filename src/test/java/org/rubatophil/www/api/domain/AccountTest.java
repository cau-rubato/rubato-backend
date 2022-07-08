package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.type.AccountStatus;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
public class AccountTest {

    @PersistenceContext
    private EntityManager em;

    Account account;

    @BeforeEach
    void setUp() {
        this.account = Account.builder()
                .loginId("test_id")
                .loginPw("test_pw")
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
}