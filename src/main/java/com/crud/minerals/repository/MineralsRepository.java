package com.crud.minerals.repository;

import com.crud.minerals.domain.Mineral;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface MineralsRepository extends CrudRepository<Mineral, Long> {

    @Override
    List<Mineral> findAll();

    @Override
    Optional<Mineral> findById (Long id);

    @Query
    List<Mineral> retrieveByName(@Param("NAME") String name);

    @Query
    List<Mineral> retrieveByColor(@Param("COLOR") String color);

    @Query
    List<Mineral> retrieveByShine(@Param("SHINE") String shine);

    @Query
    List<Mineral> retrieveByFragility(@Param("FRAGILITY") String fragility);

    @Query
    List<Mineral> retrieveByTransparency(@Param("TRANSPARENCY") String transparency);

    @Query
    List<Mineral> retrieveByOpalescence(@Param("OPALESCENCE") char opalescence);

    @Query
    List<Mineral> retrieveByRegion(@Param("REGION") String region);

    @Query
    List<Mineral> retrieveByDifferentParameters(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "COLOR", required = false) String color,
                                                @RequestParam(value = "SHINE", required = false) String shine, @RequestParam(value = "FRAGILITY", required = false) String fragility,
                                                @RequestParam(value = "TRANSPARENCY", required = false) String transparency, @RequestParam(value = "OPALESCENCE", required = false)
                                                        Character opalescence, @RequestParam(value = "REGION", required = false) String region);
}
