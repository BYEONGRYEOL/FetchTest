package com.sbl.fetchstudy.entity;


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
public class D_MTO_TW_FE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "dependent",  fetch = FetchType.EAGER)
    private List<O_MTO_TW_WFE> ownerList = new ArrayList<>();


    public D_MTO_TW_FE(String name) {
        this.name = name;
    }
}
