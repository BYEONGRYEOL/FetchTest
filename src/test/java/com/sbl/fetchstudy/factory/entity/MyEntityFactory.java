package com.sbl.fetchstudy.factory.entity;

import com.sbl.fetchstudy.entity.D_MTO_TW_FE;
import com.sbl.fetchstudy.entity.O_MTO_TW_WFE;

public class MyEntityFactory {
    static int ownerMTOTwoWayFetchEagerNumber = 1;
    static char dependentMTOTwoWayFetchEagerNumber = 'a';
    static int ownerMTOTwoWayFetchLazyNumber = 1;
    static char dependentMTOTwoWayFetchLazyNumber = 'a';
    public static O_MTO_TW_WFE createOwnerMTOTwoWayFetchEager(){
        return new O_MTO_TW_WFE("owner_" + ownerMTOTwoWayFetchEagerNumber++);
    }
    public static D_MTO_TW_FE createDependentMTOTwoWayFetchEager(){
        return new D_MTO_TW_FE("dependent_" + dependentMTOTwoWayFetchEagerNumber++);
    }
    // TODO: 2023-11-13 FetchLazy create 함수
}
