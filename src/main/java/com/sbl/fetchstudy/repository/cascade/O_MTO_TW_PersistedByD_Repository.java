package com.sbl.fetchstudy.repository.cascade;

import com.sbl.fetchstudy.entity.cascade.O_MTO_TW_PersistByD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface O_MTO_TW_PersistedByD_Repository extends JpaRepository<O_MTO_TW_PersistByD, Long> {
    public Optional<O_MTO_TW_PersistByD> findByName(String name);
}
