package com.sbl.fetchstudy.repository.cascade;

import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_PersistByD;
import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_RemoveByD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface O_MTO_TW_RemoveByD_Repository extends JpaRepository<O_MTO_TW_RemoveByD, Long> {
    public Optional<O_MTO_TW_RemoveByD> findByName(String name);
}
