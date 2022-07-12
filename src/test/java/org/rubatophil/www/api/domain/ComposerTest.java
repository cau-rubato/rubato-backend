package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.mapping.concertPiece.ConcertPiece;
import org.rubatophil.www.api.domain.mapping.concertPiece.RegularConcertPiece;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
class ComposerTest {

    @PersistenceContext
    EntityManager em;

    private Composer composer;
    private Piece piece;

    @BeforeEach
    void setUp() {
        composer = Composer.builder()
                .name("Beethoven")
                .build();

        piece = Piece.builder()
                .name("Symphony No.9")
                .build();

        em.persist(composer);
        em.persist(piece);
    }

    @Test
    @DisplayName("Builder로 Composer가 잘 생성되고 persist 시 올바른 값이 저장되는지 테스트")
    public void builderTest() throws Exception {
        //given

        //when
        Composer findComposer = em.find(Composer.class, composer.getId());

        //then
        assertEquals("Beethoven", findComposer.getName());

    }

    @Test
    @DisplayName("addPiece 메소드가 잘 동작하고 저장이 잘 되는지 테스트")
    public void addPieceTest() throws Exception {
        //given
        composer.addPiece(piece);

        //when
        Composer findComposer = em.find(Composer.class, composer.getId());
        Piece findPiece = em.find(Piece.class, piece.getId());

        //then
        assertEquals("Symphony No.9", findComposer.getPieces().get(0).getName());
        assertEquals("Beethoven", findPiece.getComposer().getName());
    }

}