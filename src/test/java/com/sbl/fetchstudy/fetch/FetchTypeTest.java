package com.sbl.fetchstudy.fetch;

import com.sbl.fetchstudy.entity.D_MTO_TW_FE;
import com.sbl.fetchstudy.entity.D_MTO_TW_FL;
import com.sbl.fetchstudy.entity.O_MTO_TW_WFE;
import com.sbl.fetchstudy.factory.entity.MyEntityFactory;
import com.sbl.fetchstudy.repository.D_MTO_TW_FE_Repository;
import com.sbl.fetchstudy.repository.D_MTO_TW_FL_Repository;
import com.sbl.fetchstudy.repository.O_MTO_TW_WFE_Repository;
import com.sbl.fetchstudy.repository.O_MTO_TW_WFL_Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@ActiveProfiles("test")
@DataJpaTest // jpa 관련 설정 및 Repository만 Bean으로 등록하여 가볍다
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FetchTypeTest {

    @Autowired
    O_MTO_TW_WFE_Repository ownerWithFetchEagerRepository;
    @Autowired
    O_MTO_TW_WFL_Repository ownerWithFetchLazyRepository;
    @Autowired
    D_MTO_TW_FE_Repository dependentFetchEagerRepository;
    @Autowired
    D_MTO_TW_FL_Repository dependentFetchLazyRepository;
    @PersistenceContext
    EntityManager entityManager; // repo test간 Transaction 성질을 무시하고 flush, delete cache 하기 위해


    @Test
    @DisplayName("Owner 가 Dependent를 Eager 조회")
    void findByOwnerTest() {
        saveDatas();
        clearPersistence();
        O_MTO_TW_WFE owner = ownerWithFetchEagerRepository.findById(1L).orElseThrow(NullPointerException::new);
        System.out.println("owner.getName() = " + owner.getName());
        System.out.println("food.getUser().getName() = " + owner.getDependent().getName());
    }

    @Test
    @DisplayName("Dependent 가 Owner를 Eager 조회")
    void findByDependentEagerTest(){
        saveDatas();
        clearPersistence();
        D_MTO_TW_FE dependent = dependentFetchEagerRepository.findById(1L).orElseThrow(NullPointerException::new);
        System.out.println("dependent.getName() = " + dependent.getName());

        dependent.getOwnerList().forEach((owner)->
                System.out.println(owner.getName())
        );
    }
    
    @Test
    @DisplayName("dependent 가 Owner를 Lazy 조회1")
    void findByDependentLazyTest(){
        saveDatas();
        clearPersistence();
        D_MTO_TW_FL dependent = dependentFetchLazyRepository.findById(1L).orElseThrow(NullPointerException::new);
        System.out.println("dependent.getName() = " + dependent.getName());
        dependent.getOwnerList().forEach((owner) ->
                System.out.println(owner.getName()));
    }


    void saveDatas() {

        List<D_MTO_TW_FE> dependentList = new ArrayList<>();
        dependentList.add(MyEntityFactory.createDependentMTOTwoWayFetchEager());
        dependentList.add(MyEntityFactory.createDependentMTOTwoWayFetchEager());
        dependentFetchEagerRepository.saveAll(dependentList);

        List<O_MTO_TW_WFE> ownerList = new ArrayList<>();
        ownerList.add(MyEntityFactory.createOwnerMTOTwoWayFetchEager());
        ownerList.add(MyEntityFactory.createOwnerMTOTwoWayFetchEager());
        ownerList.add(MyEntityFactory.createOwnerMTOTwoWayFetchEager());
        ownerList.add(MyEntityFactory.createOwnerMTOTwoWayFetchEager());
        ownerList.add(MyEntityFactory.createOwnerMTOTwoWayFetchEager());

        // 외래 키 설정
        ownerList.get(0).setDependent(dependentList.get(0));
        ownerList.get(1).setDependent(dependentList.get(0));
        ownerList.get(2).setDependent(dependentList.get(0));
        ownerList.get(3).setDependent(dependentList.get(1));
        ownerList.get(4).setDependent(dependentList.get(1));

        ownerWithFetchEagerRepository.saveAll(ownerList);
    }


    void clearPersistence(){
        entityManager.flush();
        entityManager.clear();
    }


}