package com.crud.minerals.service;

import com.crud.minerals.configuration.MineralSpecification;
import com.crud.minerals.controller.MineralNotFoundException;
import com.crud.minerals.domain.Mineral;
import com.crud.minerals.repository.MineralsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final MineralsRepository repository;
    MineralSpecification mineralSpecification = new MineralSpecification();

    public List<Mineral> getAllMinerals() {
        return repository.findAll();
    }

    public Mineral getMineral(final Long id) throws MineralNotFoundException {
        return repository.findById(id).orElseThrow(MineralNotFoundException::new);
    }

    public Mineral saveMineral(final Mineral mineral) {
        return repository.save(mineral);
    }

    public void deleteMineral(final long id) {
        repository.deleteById(id);
    }

    public List<Mineral> retrieveByDifferentParameters(String name, String color, String shine, String fragility, String transparency,
                                                       Character opalescence, String region) {
        Specification<Mineral> filteredSpecification = mineralSpecification.findByFilters(name, color, shine, transparency, fragility, opalescence, region);
        return repository.findAll(filteredSpecification);
    }

    public List<String> retrieveAllMineralsDistinctByName() {
        return repository.retrieveDistinctByName();
    }

    public List<String> retrieveAllMineralsDistinctByColor() {
        return repository.retrieveDistinctByColor();
    }

    public List<String> retrieveAllMineralsDistinctByRegion() {
        return repository.retrieveDistinctByRegion();
    }
}