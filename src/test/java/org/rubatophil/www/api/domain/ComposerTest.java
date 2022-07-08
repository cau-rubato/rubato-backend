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

    @Test
    @DisplayName("Builder로 Composer가 잘 생성되고 persist 시 올바른 값이 저장되는지 테스트")
    void builder() {
        //given
        Composer composer = Composer.builder().name("Beethoven").build();

        //when
        em.persist(composer);
        Composer findComposer = em.find(Composer.class, composer.getId());

        //then
        assertEquals("Beethoven", findComposer.getName());

    }

    @Test
    @DisplayName("addPiece 메소드가 잘 동작하고 저장이 잘 되는지 테스트")
    void addPieceTest() {
        //given
        Composer composer = Composer.builder().name("Dvorak").build();
        Piece piece = Piece.builder().name("Symphony No.9").build();
        composer.addPiece(piece);

        //when
        em.persist(composer);
        Composer findComposer = em.find(Composer.class, composer.getId());
        Piece findPiece = em.find(Piece.class, piece.getId());

        //then
        assertEquals("Dvorak", findComposer.getName());
        assertEquals("Symphony No.9", findPiece.getName());
        assertEquals("Dvorak", findPiece.getComposer().getName());
    }

}