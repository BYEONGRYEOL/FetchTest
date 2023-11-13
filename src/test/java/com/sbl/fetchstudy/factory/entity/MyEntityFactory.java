package com.sbl.fetchstudy.factory.entity;

import com.sbl.fetchstudy.entity.D_MTO_TW_FE;
import com.sbl.fetchstudy.entity.O_MTO_TW_WFE;
import com.sbl.fetchstudy.entity.cascade.D_MTO_TW_PersistToO;
import com.sbl.fetchstudy.entity.cascade.D_MTO_TW_RemoveToO;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_PersistByD;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_RemoveByD;

public class MyEntityFactory {
    static int ownerMTOTwoWayWithFetchEagerNumber = 1;
    static char dependentMTOTwoWayFetchEagerNumber = 'a';
    static int ownerMTOTwoWayFetchLazyNumber = 1;
    static char dependentMTOTwoWayFetchLazyNumber = 'a';
    static int ownerWithFetchLazyPersistedByNumber = 1;
    static char dependentMTOTwoWayPersistToNumber = 'a';
    static int getOwnerMTOTwoWayRemoveByDNumber = 1;
    public static O_MTO_TW_WFE createOwnerMTOTwoWayWithFetchEager(){
        return new O_MTO_TW_WFE("owner_" + ownerMTOTwoWayWithFetchEagerNumber++);
    }
    public static D_MTO_TW_FE createDependentMTOTwoWayFetchEager(){
        return new D_MTO_TW_FE("dependent_" + dependentMTOTwoWayFetchEagerNumber++);
    }
    // TODO: 2023-11-13 FetchLazy create 함수
    public static D_MTO_TW_PersistToO createDependentMTOTwoWayPersistToO(){
        return new D_MTO_TW_PersistToO("dependent_" + dependentMTOTwoWayPersistToNumber++);
    }

    public static O_MTO_TW_PersistByD createOwnerMTOTwoWayPersistedByD(){
        return new O_MTO_TW_PersistByD("owner_" + ownerWithFetchLazyPersistedByNumber++);
    }

    public static O_MTO_TW_PersistByD createOwnerMTOTwoWayPersistedByD(String name) {
        return new O_MTO_TW_PersistByD(name);
    }

    public static D_MTO_TW_RemoveToO createDependentMTOTwoWayRemoveToO(){
        return new D_MTO_TW_RemoveToO("dependent_" + dependentMTOTwoWayPersistToNumber++);
    }

    public static O_MTO_TW_RemoveByD createOwnerMTOTwoWayRemoveByD(String name){
        return new O_MTO_TW_RemoveByD(name);
    }
}
