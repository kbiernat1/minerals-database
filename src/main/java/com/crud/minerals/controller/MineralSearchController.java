package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.domain.MineralDto;
import com.crud.minerals.mapper.MineralMapper;
import com.crud.minerals.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mineralSearch")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MineralSearchController {

    private final DbService dbService;
    private final MineralMapper mineralMapper;

    @GetMapping
    public String search() {
        return "mineralSearch";
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MineralDto> getMineral(@PathVariable Long id) throws MineralNotFoundException {
        return ResponseEntity.ok(mineralMapper.mapToMineralDto(dbService.getMineral(id)));
    }

    @PostMapping
    public String getMineralsByDifferentParameters(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "color", required = false) String color,
                                                             @RequestParam(name = "shine", required = false) String shine, @RequestParam(name = "fragility", required = false) String fragility,
                                                             @RequestParam(name = "transparency", required = false) String transparency, @RequestParam(name = "opalescence", required = false)
                                                                         Character opalescence, @RequestParam(name = "region", required = false) String region, Model model) {
        List<Mineral> minerals = dbService.retrieveByDifferentParameters(name, color, shine, fragility, transparency, opalescence, region);
        model.addAttribute("minerals", minerals);
        return "mineralSearch";
    }

    @GetMapping("/name")
    public List<MineralDto> getMineralsByName (@RequestParam(name = "name", required = true) String name, Model model) {
        model.addAttribute(name);
        List<Mineral> minerals = dbService.retrieveMineralsByName(name);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/color")
    public List<MineralDto> getMineralsByColor (@RequestParam(name = "color", required = true) String color) {
        List<Mineral> minerals = dbService.retrieveMineralsByColor(color);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/shine")
    public List<MineralDto> getMineralsByShine (@RequestParam(name = "shine", required = true) String shine) {
        List<Mineral> minerals = dbService.retrieveMineralsByShine(shine);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/frag")
    public List<MineralDto> getMineralsByFragility (@RequestParam(name = "fragility", required = true) String fragility) {
        List<Mineral> minerals = dbService.retrieveMineralsByFragility(fragility);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/transp")
    public List<MineralDto> getMineralsByTransparency (@RequestParam(name = "transparency", required = true) String transparency) {
        List<Mineral> minerals = dbService.retrieveMineralsByTransparency(transparency);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/opal")
    public List<MineralDto> getMineralsByOpalescence (@RequestParam(name = "opalescence", required = true) char opalescence) {
        List<Mineral> minerals = dbService.retrieveMineralsByOpalescence(opalescence);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/region")
    public List<MineralDto> getMineralsByRegion (@RequestParam(name = "region", required = true) String region) {
        List<Mineral> minerals = dbService.retrieveMineralsByRegion(region);
        return mineralMapper.mineralToDtoList(minerals);
    }
}
