package com.sbl.fetchstudy.cascade;


import com.sbl.fetchstudy.entity.cascade.D_MTO_TW_CP;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_CP;
import com.sbl.fetchstudy.factory.entity.MyEntityFactory;
import com.sbl.fetchstudy.repository.cascade.D_MTO_TW_CP_Repository;
import com.sbl.fetchstudy.repository.cascade.O_MTO_TW_WCP_Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CascadeTest {

    @Autowired
    O_MTO_TW_WCP_Repository ownerWithFetchEagerRepository;
    @Autowired
    D_MTO_TW_CP_Repository dependentFetchEagerRepository;
    @PersistenceContext
    EntityManager entityManager; // repo test간 Transaction 성질을 무시하고 flush, delete cache 하기 위해



    @Test
    @DisplayName("Robbie 음식 주문")
    void cascadeSaveTest() {
        // 고객 Robbie 가 후라이드 치킨과 양념 치킨을 주문합니다.
        D_MTO_TW_CP dependent =  MyEntityFactory.createDependentMTOTwoWayFetchLazyCascadePersist();
        O_MTO_TW_CP owner1 = MyEntityFactory.createOwnerMTOTwoWayWithFetchLazyWithCascadePersist();
        O_MTO_TW_CP owner2 = MyEntityFactory.createOwnerMTOTwoWayWithFetchLazyWithCascadePersist();

        dependent.addOwner(owner1);
        dependent.addOwner(owner2);

        dependentFetchEagerRepository.save(dependent);

        ownerWithFetchEagerRepository.findAll().forEach((owner)-> System.out.println(owner.getName()));
    }
}
