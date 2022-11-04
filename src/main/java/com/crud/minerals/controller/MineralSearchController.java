package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mineral_search")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MineralSearchController {

    private final DbService dbService;

    @GetMapping
    public String search(Model model) {
        model.addAttribute("names", dbService.retrieveAllMineralsDistinctByName());
        model.addAttribute("colors", dbService.retrieveAllMineralsDistinctByColor());
        model.addAttribute("regions", dbService.retrieveAllMineralsDistinctByRegion());
        List<Mineral> minerals = dbService.getAllMinerals();
        model.addAttribute("minerals", minerals);
        return "mineral_search";
    }

    @PostMapping()
    public String getMineralsByDifferentParameters(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "color", required = false) String color,
                                                   @RequestParam(name = "shine", required = false) String shine, @RequestParam(name = "fragility", required = false) String fragility,
                                                   @RequestParam(name = "transparency", required = false) String transparency, @RequestParam(name = "opalescence", required = false)
                                                              Character opalescence, @RequestParam(name = "region", required = false) String region, Model model) {

        List<Mineral> minerals = dbService.retrieveByDifferentParameters(name, color, shine, fragility, transparency, opalescence, region);
        model.addAttribute("minerals", minerals);
        return "redirect:/mineral_search";
    }
}