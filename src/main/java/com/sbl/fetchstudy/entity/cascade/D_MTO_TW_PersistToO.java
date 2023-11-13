package com.sbl.fetchstudy.entity.cascade;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class D_MTO_TW_PersistToO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "dependent", cascade = CascadeType.PERSIST) // 영속성이 전이되어 저장될 수 있도록
    private List<O_MTO_TW_PersistByD> ownerList = new ArrayList<>();

    // 외래키의 주인이 아닌 쪽에서 연관관계 설정해주기
    public void addOwner(O_MTO_TW_PersistByD owner){
        this.ownerList.add(owner);
        owner.setDependent(this);
    }


    public D_MTO_TW_PersistToO(String name) {
        this.name = name;
    }
}
