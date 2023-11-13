package com.sbl.fetchstudy.cascade;


import com.sbl.fetchstudy.entity.cascade.D_MTO_TW_PersistToO;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_PersistByD;
import com.sbl.fetchstudy.factory.entity.MyEntityFactory;
import com.sbl.fetchstudy.repository.cascade.D_MTO_TW_PersistToO_Repository;
import com.sbl.fetchstudy.repository.cascade.O_MTO_TW_PersistedByD_Repository;
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
public class CascadeTest {

    @Autowired
    O_MTO_TW_PersistedByD_Repository ownerPersistedByRepository;
    @Autowired
    D_MTO_TW_PersistToO_Repository dependentPersistToOwnerRepository;
    @PersistenceContext
    EntityManager entityManager; // repo test간 Transaction 성질을 무시하고 flush, delete cache 하기 위해



    @Test
    @DisplayName("persist 저장 테스트")
    void cascadeSaveTest() {

        //given 연관관계 데이터
        String testName = "testName";
        D_MTO_TW_PersistToO dependent =  MyEntityFactory.createDependentMTOTwoWayPersistToO();
        O_MTO_TW_PersistByD owner1 = MyEntityFactory.createOwnerMTOTwoWayPersistedByD(testName);
        O_MTO_TW_PersistByD owner2 = MyEntityFactory.createOwnerMTOTwoWayPersistedByD();
        dependent.addOwner(owner1);
        dependent.addOwner(owner2);
        
        // when Persist 옵션이 걸린 entity 저장
        dependentPersistToOwnerRepository.save(dependent);
        clearSession();

        // then Persist 옵션에 의해 owner 2개 저장 및 testName의 owner 존재
        assertThat(ownerPersistedByRepository.findAll().size()).isEqualTo(2); 
        O_MTO_TW_PersistByD savedOwner =ownerPersistedByRepository.findByName(testName).orElseThrow(RuntimeException::new);
        assert(Objects.equals(savedOwner.getName(), testName)); 

    }
    void clearSession(){
        entityManager.flush();
        entityManager.clear();
    }
}
