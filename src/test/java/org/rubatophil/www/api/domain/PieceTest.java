package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.concert.Concert;
import org.rubatophil.www.api.domain.concert.RegularConcert;
import org.rubatophil.www.api.domain.mapping.concertPiece.ConcertPiece;
import org.rubatophil.www.api.domain.mapping.concertPiece.RegularConcertPiece;
import org.rubatophil.www.api.domain.type.RegularConcertSection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
class PieceTest {

    @PersistenceContext
    EntityManager em;

    Piece piece;
    RegularConcert regularConcert;
    RegularConcertPiece regularConcertPiece;
    
    @BeforeEach
    void setUp() {
        piece = Piece.builder()
                .name("Symphony No.9")
                .build();

        regularConcert = RegularConcert.builder()
                .episode(58)
                .name("Resurrection")
                .build();

        regularConcertPiece = RegularConcertPiece.builder()
                .section(RegularConcertSection.FIRST)
                .build();

        em.persist(piece);
        em.persist(regularConcert);
        em.persist(regularConcertPiece);
    }

    @Test
    @DisplayName("Builder로 Piece가 잘 생성되고 persist 시 올바른 값이 저장되는지 테스트")
    public void builderTest() throws Exception {
        //given

        //when
        Piece findPiece = em.find(Piece.class, piece.getId());

        //then
        assertEquals("Symphony No.9", findPiece.getName());
    }

    @Test
    @DisplayName("addConcertPiece로 매핑 테이블과 연관관계가 잘 잡히는지 테스트")
    public void addConcertPieceTest() throws Exception {
        //given
        piece.addConcertPiece(regularConcertPiece);
        regularConcert.addRegularConcertPiece(regularConcertPiece);

        //when
        Piece findPiece = em.find(Piece.class, piece.getId());
        RegularConcertPiece findConcertPiece = (RegularConcertPiece) findPiece.getConcertPieces().get(0);

        //then
        assertEquals(RegularConcertSection.FIRST, findConcertPiece.getSection());
        assertEquals(58, findConcertPiece.getRegularConcert().getEpisode());
        assertEquals("Resurrection", findConcertPiece.getRegularConcert().getName());
    }
}