package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.domain.MineralDto;
import com.crud.minerals.mapper.MineralMapper;
import com.crud.minerals.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base/minerals")
@RequiredArgsConstructor
public class MineralSearchController {

    private final DbService dbService;
    private final MineralMapper mineralMapper;


    @GetMapping(value = "{id}")
    public ResponseEntity<MineralDto> getMineral(@PathVariable Long id) throws MineralNotFoundException {
        return ResponseEntity.ok(mineralMapper.mapToMineralDto(dbService.getMineral(id)));
    }

    @GetMapping("/name")
    public List<MineralDto> getMineralsByName (@RequestBody String name) {
        List<Mineral> minerals = dbService.retrieveMineralsByName(name);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/color")
    public List<MineralDto> getMineralsByColor (@RequestBody String color) {
        List<Mineral> minerals = dbService.retrieveMineralsByColor(color);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/shine")
    public List<MineralDto> getMineralsByShine (@RequestBody String shine) {
        List<Mineral> minerals = dbService.retrieveMineralsByShine(shine);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/frag")
    public List<MineralDto> getMineralsByFragility (@RequestBody String fragility) {
        List<Mineral> minerals = dbService.retrieveMineralsByFragility(fragility);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/transp")
    public List<MineralDto> getMineralsByTransparency (@RequestBody String transparency) {
        List<Mineral> minerals = dbService.retrieveMineralsByTransparency(transparency);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/opal")
    public List<MineralDto> getMineralsByOpalescence (@RequestBody char opalescence) {
        List<Mineral> minerals = dbService.retrieveMineralsByOpalescence(opalescence);
        return mineralMapper.mineralToDtoList(minerals);
    }

    @GetMapping("/region")
    public List<MineralDto> getMineralsByRegion (@RequestBody String region) {
        List<Mineral> minerals = dbService.retrieveMineralsByRegion(region);
        return mineralMapper.mineralToDtoList(minerals);
    }
}
