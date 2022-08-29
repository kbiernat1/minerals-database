package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.domain.MineralDto;
import com.crud.minerals.mapper.MineralMapper;
import com.crud.minerals.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminPanel")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final MineralMapper mineralMapper;
    private final DbService dbService;

    @GetMapping
    public String welcome() {
        return "adminPanel";
    }

    @GetMapping("/minerals")
    public ResponseEntity<List<MineralDto>> getMinerals () {
        List<Mineral> minerals = dbService.getAllMinerals();
        return ResponseEntity.ok(mineralMapper.mineralToDtoList(minerals));
    }

    @GetMapping(value = "/minerals/{id}")
    public ResponseEntity<MineralDto> getMineral(@PathVariable Long id) throws MineralNotFoundException {
        return ResponseEntity.ok(mineralMapper.mapToMineralDto(dbService.getMineral(id)));
    }

    @DeleteMapping(value = "/minerals/{id}")
    @ResponseBody
    public void deleteMineral(@PathVariable Long id) throws MineralNotFoundException {
        if (dbService.getMineral(id) != null) {
            dbService.deleteMineral(id);
        } else {
            throw new MineralNotFoundException();
        }
    }

    @PutMapping("/minerals")
    public ResponseEntity<MineralDto> updateMineral(@RequestBody MineralDto mineralDto) {
        Mineral mineral = mineralMapper.mapToMineral(mineralDto);
        Mineral savedMineral = dbService.saveMineral(mineral);
        return ResponseEntity.ok(mineralMapper.mapToMineralDto(savedMineral));
    }

    @PostMapping(path = "/minerals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createMineral(@RequestBody MineralDto mineralDto) {
        Mineral mineral = mineralMapper.mapToMineral(mineralDto);
        dbService.saveMineral(mineral);
        return ResponseEntity.ok().build();
    }
}
