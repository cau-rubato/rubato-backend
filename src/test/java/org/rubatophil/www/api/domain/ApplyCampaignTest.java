package org.rubatophil.www.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rubatophil.www.api.domain.type.ApplyStatus;
import org.rubatophil.www.api.domain.type.DocumentStatus;
import org.rubatophil.www.api.domain.type.Instrument;
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
class ApplyCampaignTest {

    @PersistenceContext
    private EntityManager em;

    ApplyCampaign applyCampaign;

    @BeforeEach
    void setUp() {
        this.applyCampaign = ApplyCampaign.builder()
                .generation(34)
                .openedAt(LocalDateTime.of(2022, 1, 1, 0, 0, 0, 0))
                .closedAt(LocalDateTime.of(2022, 3, 1, 0,0, 0, 0))
                .build();

        em.persist(this.applyCampaign);
    }

    @Test
    @DisplayName("Apply Campaign Table의 status field PrePersist가 잘 적용되는지 테스트")
    public void statusPrePersistTest() throws Exception {

        //given
        //when
        //then
        ApplyCampaign emfindApplyCampaign = em.find(ApplyCampaign.class, this.applyCampaign.getId());

        assertEquals(ApplyStatus.OPENED, emfindApplyCampaign.getStatus());
    }

    @Test
    @DisplayName("Apply Campaign Table의 Builder가 잘 작동하는지 테스트")
    public void ApplyCampaignBuilderTest() throws Exception {

        //given
        //when
        //then
        ApplyCampaign emfindApplyCampaign = em.find(ApplyCampaign.class, this.applyCampaign.getId());

        assertEquals(34, emfindApplyCampaign.getGeneration());
        assertEquals(LocalDateTime.of(2022, 1, 1, 0, 0, 0, 0), emfindApplyCampaign.getOpenedAt());
        assertEquals(LocalDateTime.of(2022, 3, 1, 0, 0, 0, 0), emfindApplyCampaign.getClosedAt());
    }

    @Test
    @DisplayName("Apply Campaign Table에 한 개의 Apply가 잘 들어가고 조회되는지 테스트")
    public void addOneApplyTest() throws Exception {

        //given
        Apply apply = Apply.builder()
                .preferredInstrument(Instrument.VIOLIN)
                .introduction("test introduction")
                .documentStatus(DocumentStatus.TEMPORARY)
                .build();

        em.persist(apply);

        //when
        this.applyCampaign.addApply(apply);

        //then
        ApplyCampaign emfindApplyCampaign = em.find(ApplyCampaign.class, this.applyCampaign.getId());
        Apply emfindApply = em.find(Apply.class, apply.getId());

        assertEquals(this.applyCampaign.getApplies().get(0), emfindApplyCampaign.getApplies().get(0));
        assertEquals(emfindApplyCampaign, emfindApply.getApplyCampaign());
    }
}