package com.crud.minerals.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MineralDto {
    private Long id;
    private String name;
    private String color;
    private String shine;
    private String transparency;
    private String fragility;
    private char opalescence;
    private String region;
}
