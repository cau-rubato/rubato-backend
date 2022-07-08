package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.rubatophil.www.api.domain.type.ManagerType;
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
public class ManagerTest {

    @PersistenceContext
    private EntityManager em;

    Manager manager;

    @BeforeEach
    void setUp() {
        this.manager = Manager.builder()
                .managerType(ManagerType.PRESIDENT)
                .startedAt(LocalDate.of(2022, 01, 01))
                .build();

        em.persist(this.manager);
    }

    @Test
    @DisplayName("Manager Table의 status field에 PrePersist가 잘 적용되는지 테스트")
    public void statusPrePersistTest() throws Exception {

        //given
        //when
        //then
        Manager emfindManager = em.find(Manager.class, this.manager.getId());

        assertEquals(ManagerStatus.ACTIVATED, emfindManager.getStatus());
    }

    @Test
    @DisplayName("Manager Builder가 잘 작동하는지 테스트")
    public void managerBuilderTest() throws Exception {

        //given
        //when
        //then
        Manager emfindManager = em.find(Manager.class, this.manager.getId());

        assertEquals(ManagerType.PRESIDENT, emfindManager.getManagerType());
        assertEquals(LocalDate.of(2022, 01, 01), emfindManager.getStartedAt());
    }

    @Test
    @DisplayName("Manager Table에 한 개의 Club Member가 잘 들어가고 조회되는지 테스트")
    public void addOneClubMemberTest() throws Exception {

        //given
        Account account = Account.builder()
                .loginId("test_id")
                .loginPw("test_pw")
                .build();

        em.persist(account);

        Address address = Address.builder()
                .zipcode("00000")
                .state("서울특별시")
                .city("동작구")
                .town("흑석동")
                .fullAddress("서울특별시 동작구 흑석로 84 중앙대학교 서울캠퍼스 학생회관 607호")
                .build();

        Department department = Department.builder()
                .college("창의ICT공과대학")
                .school("소프트웨어학부")
                .build();

        em.persist(department);

        ClubMember clubMember = ClubMember.builder()
                .account(account)
                .name("test club member name")
                .birth(LocalDate.of(1999, 01, 01))
                .phoneNumber("01000000000")
                .address(address)
                .generation(34)
                .department(department)
                .studentId("20180000")
                .build();

        em.persist(clubMember);

        //when
        this.manager.addClubMember(clubMember);

        //then
        Manager emfindManager = em.find(Manager.class, this.manager.getId());
        ClubMember emfindClubMember = em.find(ClubMember.class, clubMember.getId());

        assertEquals(this.manager.getClubMember(), emfindManager.getClubMember());
        assertEquals(emfindManager, emfindClubMember.getManager());
    }
}
