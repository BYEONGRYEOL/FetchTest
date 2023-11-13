package com.sbl.fetchstudy.factory.entity;

import com.sbl.fetchstudy.entity.D_MTO_TW_FE;
import com.sbl.fetchstudy.entity.O_MTO_TW_WFE;
import com.sbl.fetchstudy.entity.cascade.D_MTO_TW_PersistToO;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_PersistByD;

public class MyEntityFactory {
    static int ownerMTOTwoWayWithFetchEagerNumber = 1;
    static char dependentMTOTwoWayFetchEagerNumber = 'a';
    static int ownerMTOTwoWayFetchLazyNumber = 1;
    static char dependentMTOTwoWayFetchLazyNumber = 'a';
    static int ownerMTOTwoWayWithFetchLazyWithCascadePersistNumber = 1;
    static char dependentMTOTwoWayFetchLazyCascadePersistNumber = 'a';
    public static O_MTO_TW_WFE createOwnerMTOTwoWayWithFetchEager(){
        return new O_MTO_TW_WFE("owner_" + ownerMTOTwoWayWithFetchEagerNumber++);
    }
    public static D_MTO_TW_FE createDependentMTOTwoWayFetchEager(){
        return new D_MTO_TW_FE("dependent_" + dependentMTOTwoWayFetchEagerNumber++);
    }
    // TODO: 2023-11-13 FetchLazy create 함수
    public static D_MTO_TW_PersistToO createDependentMTOTwoWayPersistToO(){
        return new D_MTO_TW_PersistToO("dependent_" + dependentMTOTwoWayFetchLazyCascadePersistNumber++);
    }

    public static O_MTO_TW_PersistByD createOwnerMTOTwoWayPersistedByD(){
        return new O_MTO_TW_PersistByD("owner_" + ownerMTOTwoWayWithFetchLazyWithCascadePersistNumber++);
    }

    public static O_MTO_TW_PersistByD createOwnerMTOTwoWayPersistedByD(String name) {
        return new O_MTO_TW_PersistByD(name);
    }
}
