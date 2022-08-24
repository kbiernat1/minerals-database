package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.domain.MineralDto;
import com.crud.minerals.mapper.MineralMapper;
import com.crud.minerals.repository.MineralsRepository;
import com.crud.minerals.repository.SearchOps;
import com.crud.minerals.specification.MineralSpecification;
import com.crud.minerals.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mineralSearch")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MineralSearchController {

    private MineralsRepository mineralsRepository;
    private MineralMapper mineralMapper;

    @GetMapping
    public String search() {
        return "mineralSearch";
    }

    // 2) Attempt using Specification

    @PostMapping
    void specification(@RequestBody List<SearchCriteria> searchCriteria, Model model) {
        MineralSpecification mineralSpecification = new MineralSpecification();
        searchCriteria.stream()
                .map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), (SearchOps) searchCriterion.getValue(), searchCriterion.getOperation()))
                .forEach(mineralSpecification::add);
        List<Mineral> minerals = mineralsRepository.findAll(mineralSpecification);
        model.addAttribute("minerals", minerals);
    }
}

   /*

   1) Attempt using @RequestParam
   ------------------------------

   @PostMapping()
    public String getMineralsByDifferentParameters(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "color", required = false) String color,
                                                   @RequestParam(name = "shine", required = false) String shine, @RequestParam(name = "fragility", required = false) String fragility,
                                                   @RequestParam(name = "transparency", required = false) String transparency, @RequestParam(name = "opalescence", required = false)
                                                           Character opalescence, @RequestParam(name = "region", required = false) String region, Model model) {

        List<Mineral> minerals = dbService.retrieveByDifferentParameters(name, color, shine, fragility, transparency, opalescence, region);
        model.addAttribute("minerals", minerals);
        return "mineralSearch";
    }
    ------------------------------
    */


    /*private final DbService dbService;
    private final MineralMapper mineralMapper;

    @GetMapping(value = "{id}")
    public ResponseEntity<MineralDto> getMineral(@PathVariable Long id) throws MineralNotFoundException {
        return ResponseEntity.ok(mineralMapper.mapToMineralDto(dbService.getMineral(id)));
    }

    @GetMapping("/name")
    @ResponseBody
    public List<MineralDto> getMineralsByName (@RequestParam(name = "name") String name) {
        List<Mineral> minerals = dbService.retrieveMineralsByName(name);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/color")
    @ResponseBody
    public List<MineralDto> getMineralsByColor (@RequestParam(name = "color") String color) {
        List<Mineral> minerals = dbService.retrieveMineralsByColor(color);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/shine")
    @ResponseBody
    public List<MineralDto> getMineralsByShine (@RequestParam(name = "shine") String shine) {
        List<Mineral> minerals = dbService.retrieveMineralsByShine(shine);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/frag")
    @ResponseBody
    public List<MineralDto> getMineralsByFragility (@RequestParam(name = "fragility", required = true) String fragility) {
        List<Mineral> minerals = dbService.retrieveMineralsByFragility(fragility);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/transp")
    @ResponseBody
    public List<MineralDto> getMineralsByTransparency (@RequestParam(name = "transparency") String transparency) {
        List<Mineral> minerals = dbService.retrieveMineralsByTransparency(transparency);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/opal")
    @ResponseBody
    public List<MineralDto> getMineralsByOpalescence (@RequestParam(name = "opalescence") char opalescence) {
        List<Mineral> minerals = dbService.retrieveMineralsByOpalescence(opalescence);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/region")
    @ResponseBody
    public List<MineralDto> getMineralsByRegion (@RequestParam(name = "region") String region) {
        List<Mineral> minerals = dbService.retrieveMineralsByRegion(region);
        return mineralMapper.mineralToDtoList(minerals);
    }*/

