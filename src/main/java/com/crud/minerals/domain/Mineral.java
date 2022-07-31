package com.crud.minerals.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(
                name = "Mineral.retrieveByName",
                query = "FROM Mineral WHERE name = :NAME"
        ),
        @NamedQuery(
                name = "Mineral.retrieveByColor",
                query = "FROM Mineral WHERE name = :COLOR"
        ),
        @NamedQuery(
                name = "Mineral.retrieveByShine",
                query = "FROM Mineral WHERE name = :SHINE"
        ),
        @NamedQuery(
                name = "Mineral.retrieveByTransparency",
                query = "FROM Mineral WHERE name = :TRANSPARENCY"
        ),
        @NamedQuery(
                name = "Mineral.retrieveByFragility",
                query = "FROM Mineral WHERE name = :FRAGILITY"
        ),
        @NamedQuery(
                name = "Mineral.retrieveByOpalescence",
                query = "FROM Mineral WHERE name = :OPALESCENCE"
        ),
        @NamedQuery(
                name = "Mineral.retrieveByRegion",
                query = "FROM Mineral WHERE name = :REGION"
        ),
})

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
}

