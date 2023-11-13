package com.sbl.fetchstudy.cascade;


import com.sbl.fetchstudy.entity.cascade.D_MTO_TW_PersistToO;
import com.sbl.fetchstudy.entity.cascade.D_MTO_TW_RemoveToO;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_PersistByD;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_RemoveByD;
import com.sbl.fetchstudy.factory.entity.MyEntityFactory;
import com.sbl.fetchstudy.repository.cascade.D_MTO_TW_PersistToO_Repository;
import com.sbl.fetchstudy.repository.cascade.D_MTO_TW_RemoveToO_Repository;
import com.sbl.fetchstudy.repository.cascade.O_MTO_TW_PersistedByD_Repository;
import com.sbl.fetchstudy.repository.cascade.O_MTO_TW_RemoveByD_Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RemoveTest {

    @Autowired
    O_MTO_TW_RemoveByD_Repository ownerRemoveByRepository;
    @Autowired
    D_MTO_TW_RemoveToO_Repository dependentRemoveToOwnerRepository;


    @PersistenceContext
    EntityManager entityManager; // repo test간 Transaction 성질을 무시하고 flush, delete cache 하기 위해

    @Test
    @DisplayName("cascade Remove 삭제시 전파 테스트")
    void cascadeRemoveDeleteTest() {
        //given 연관 관계 데이터 저장 및 flush
        String testName1 = "testName1";
        String testName2 = "testName2";

        D_MTO_TW_RemoveToO dependent =  MyEntityFactory.createDependentMTOTwoWayRemoveToO();
        O_MTO_TW_RemoveByD owner1 = MyEntityFactory.createOwnerMTOTwoWayRemoveByD(testName1);
        O_MTO_TW_RemoveByD owner2 = MyEntityFactory.createOwnerMTOTwoWayRemoveByD(testName2);
        dependent.addOwner(owner1);
        dependent.addOwner(owner2);
        dependentRemoveToOwnerRepository.save(dependent);
        clearSession();

        // when Cascade.Remove 옵션을 가진 entity를 삭제
        dependentRemoveToOwnerRepository.delete(dependent);
        // flush 이전엔 연관관계 저장에 의해 저장되어있는 상태 
        assertThat(ownerRemoveByRepository.findByName(testName1)).isNotNull();
        clearSession();

        // then Remove 옵션에 의해 owner에 저장된 entity 삭제
        assertThat(ownerRemoveByRepository.findAll().size()).isEqualTo(0);
    }
    void clearSession(){
        entityManager.flush();
        entityManager.clear();
    }
}
