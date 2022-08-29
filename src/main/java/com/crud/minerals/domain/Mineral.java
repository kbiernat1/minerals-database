package com.crud.minerals.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedNativeQuery(
        name = "Mineral.retrieveByDifferentParameters",
        query = "SELECT * FROM minerals WHERE IFNULL(:name, name) = IFNULL(name, name) AND " +
                "IFNULL(:color, color) = IFNULL(color, color) AND " +
                "IFNULL(:shine, shine) = IFNULL(shine, shine) AND " +
                "IFNULL(:transparency, transparency) = IFNULL(transparency, transparency) AND " +
                "IFNULL(:fragility, fragility) = IFNULL(fragility, fragility) AND " +
                "IFNULL(:opalescence, opalescence) = IFNULL(opalescence, opalescence) AND " +
                "IFNULL(:region, region) = IFNULL(region, region);",
        resultClass = Mineral.class
)

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "minerals")
public class Mineral {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "shine")
    private String shine;

    @Column(name = "transparency")
    private String transparency;

    @Column(name = "fragility")
    private String fragility;

    @Column(name = "opalescence")
    private char opalescence;

    @Column(name = "region")
    private String region;

    @Override
    public String toString() {
        return "name: " + name + ", color: " + color + ", shine level: " + shine + ", transparency level: " + transparency + ", fragility level: " + fragility
                + ", is opalescent: " + opalescence + ", region (PL): " + region;
    }
}

