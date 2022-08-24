package com.crud.minerals.service;

import com.crud.minerals.controller.MineralNotFoundException;
import com.crud.minerals.domain.Mineral;
import com.crud.minerals.repository.MineralsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final MineralsRepository repository;

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



    /*
    1) Attempt using @RequestParam
    ------------------------------

    public List<Mineral> retrieveByDifferentParameters(String name, String color, String shine, String fragility, String transparency,
                                                       Character opalescence, String region) {
        return repository.retrieveByDifferentParameters(name, color, shine, fragility, transparency, opalescence, region);
    }
    ------------------------------
    */



    /*public List<Mineral> retrieveMineralsByName(String name) {
        return repository.retrieveByName(name);
    }

    public List<Mineral> retrieveMineralsByColor(String color) {
        return repository.retrieveByColor(color);
    }

    public List<Mineral> retrieveMineralsByShine(String shine) {
        return repository.retrieveByShine(shine);
    }

    public List<Mineral> retrieveMineralsByFragility(String fragility) {
        return repository.retrieveByFragility(fragility);
    }

    public List<Mineral> retrieveMineralsByTransparency(String transparency) {
        return repository.retrieveByTransparency(transparency);
    }

    public List<Mineral> retrieveMineralsByOpalescence(char opalescence) {
        return repository.retrieveByOpalescence(opalescence);
    }

    public List<Mineral> retrieveMineralsByRegion(String region) {
        return repository.retrieveByRegion(region);
    }*/
}
