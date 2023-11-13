package com.sbl.fetchstudy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class O_MTO_TW_WFL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dependent_id") // 아래 변수가 참조하는 테이블의 id와 연관을 맺어줘
    private D_MTO_TW_FL dependent = new D_MTO_TW_FL();

    public O_MTO_TW_WFL(String name) {
        this.name = name;
    }
}
