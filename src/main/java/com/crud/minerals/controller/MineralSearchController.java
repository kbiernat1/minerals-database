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

   /* @PostMapping()
    public String getMineralsByDifferentParameters(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "color", required = false) String color,
                                                   @RequestParam(name = "shine", required = false) String shine, @RequestParam(name = "fragility", required = false) String fragility,
                                                   @RequestParam(name = "transparency", required = false) String transparency, @RequestParam(name = "opalescence", required = false)
                                                           Character opalescence, @RequestParam(name = "region", required = false) String region, Model model) {

        List<Mineral> minerals = dbService.retrieveByDifferentParameters(name, color, shine, fragility, transparency, opalescence, region);
        model.addAttribute("minerals", minerals);
        return "mineralSearch";
    }*/

    @GetMapping("/mineralSearch")
    public ResponseEntity<List<MineralDto>> getMinerals (@RequestParam(name = "name", required = false) String name, @RequestParam(name = "color", required = false) String color,
                                                         @RequestParam(name = "shine", required = false) String shine, @RequestParam(name = "fragility", required = false) String fragility,
                                                         @RequestParam(name = "transparency", required = false) String transparency, @RequestParam(name = "opalescence", required = false)
                                                                     Character opalescence, @RequestParam(name = "region", required = false) String region, Model model) {
        List<Mineral> minerals = dbService.retrieveByDifferentParameters(name, color, shine, fragility, transparency, opalescence, region);
        return ResponseEntity.ok(mineralMapper.mineralToDtoList(minerals));
    }
}