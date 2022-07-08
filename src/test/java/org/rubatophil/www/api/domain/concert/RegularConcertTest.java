package org.rubatophil.www.api.domain.concert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.Composer;
import org.rubatophil.www.api.domain.Piece;
import org.rubatophil.www.api.domain.mapping.concertPiece.RegularConcertPiece;
import org.rubatophil.www.api.domain.type.Address;
import org.rubatophil.www.api.domain.type.ApplyStatus;
import org.rubatophil.www.api.domain.type.Location;
import org.rubatophil.www.api.domain.type.RegularConcertSection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
public class RegularConcertTest {

    @PersistenceContext
    private EntityManager em;

    Address concertHallAddress;
    Location concertHallLocation;
    RegularConcert regularConcert;

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
                .date(LocalDateTime.of(2022, 07, 07, 20, 0, 0, 0))
                .location(concertHallLocation)
                .posterUrl("www.poster.com")
                .episode(0)
                .build();

        em.persist(this.concertHallLocation);
        em.persist(this.regularConcert);
    }

    @Test
    @DisplayName("Regular Concert Table의 applyStatus, fee field에 PrePersist가 잘 적용되는지 테스트")
    public void feePrePersistTest() throws Exception {

        //given
        //when
        //then
        assertEquals(this.regularConcert.getApplyStatus(), ApplyStatus.CLOSED);
        assertEquals(this.regularConcert.getFee(), 0);
    }

    @Test
    @DisplayName("Regular Concert Builder가 잘 작동하는지 테스트")
    public void regularConcertBuilderTest() throws Exception {

        //given
        //when
        //then
        assertEquals(this.regularConcert.getName(), "test regular concert");
        assertEquals(this.regularConcert.getDate(), LocalDateTime.of(2022, 07, 07, 20, 0, 0, 0));
        assertEquals(this.regularConcert.getLocation(), concertHallLocation);
        assertEquals(this.regularConcert.getPosterUrl(), "www.poster.com");
        assertEquals(this.regularConcert.getEpisode(), 0);
    }

    @Test
    @DisplayName("Regular Concert Table에 한 개의 Regular Concert Piece가 잘 들어가고 조회되는지 테스트")
    public void addOneRegularConcertPieceTest() throws Exception {

        //given
        Composer firstPieceComposer = Composer.builder()
                .name("first piece composer name")
                .build();

        em.persist(firstPieceComposer);

        Piece firstPiece = Piece.builder()
                .name("first piece name")
                .composer(firstPieceComposer)
                .build();

        em.persist(firstPiece);

        RegularConcertPiece firstConcertPiece = RegularConcertPiece.builder()
                .piece(firstPiece)
                .section(RegularConcertSection.FIRST)
                .build();

        em.persist(firstConcertPiece);

        //when
        this.regularConcert.addRegularConcertPiece(firstConcertPiece);

        //then
        RegularConcert emfindRegularConcert = em.find(RegularConcert.class, regularConcert.getId());
        RegularConcertPiece emfindRegularConcertPiece = em.find(RegularConcertPiece.class, firstConcertPiece.getId());

        assertEquals(this.regularConcert.getRegularConcertPieces().get(0), emfindRegularConcert.getRegularConcertPieces().get(0));
        assertEquals(emfindRegularConcertPiece.getRegularConcert(), emfindRegularConcert);

    }
}
