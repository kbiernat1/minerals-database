package com.crud.minerals.repository;

import com.crud.minerals.domain.Mineral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface MineralsRepository extends JpaRepository<Mineral, Long>, JpaSpecificationExecutor<Mineral> {

    @Override
    List<Mineral> findAll();

    @Override
    Optional<Mineral> findById (Long id);

    @Query(nativeQuery = true)
    List<Mineral> retrieveByDifferentParameters(@RequestParam(value = "NAME", required = false) String name, @RequestParam(value = "COLOR", required = false) String color,
                                                @RequestParam(value = "SHINE", required = false) String shine, @RequestParam(value = "FRAGILITY", required = false) String fragility,
                                                @RequestParam(value = "TRANSPARENCY", required = false) String transparency, @RequestParam(value = "OPALESCENCE", required = false)
                                                        Character opalescence, @RequestParam(value = "REGION", required = false) String region);

    @Query(nativeQuery = true)
    List<String> retrieveDistinctByName();

    @Query(nativeQuery = true)
    List<String> retrieveDistinctByColor();

    @Query(nativeQuery = true)
    List<String> retrieveDistinctByRegion();
}
