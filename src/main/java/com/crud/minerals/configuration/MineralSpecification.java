package com.crud.minerals.configuration;

import com.crud.minerals.domain.Mineral;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class MineralSpecification implements Specification<Mineral> {

    @Override
    public Specification<Mineral> and(Specification<Mineral> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Mineral> or(Specification<Mineral> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Mineral> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    public static Specification<Mineral> bySearch(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            Predicate namePredicate = criteriaBuilder.like(root.<String>get("name"), "%" + search + "%");
            Predicate colorPredicate = criteriaBuilder.like(root.<String>get("color"), "%" + search + "%");
            Predicate shinePredicate = criteriaBuilder.like(root.<String>get("shine"), "%" + search + "%");
            Predicate transparencyPredicate = criteriaBuilder.like(root.<String>get("transparency"), "%" + search + "%");
            Predicate fragilityPredicate = criteriaBuilder.like(root.<String>get("fragility"), "%" + search + "%");
            Predicate opalescencePredicate = criteriaBuilder.like(root.<String>get("opalescence"), "%" + search + "%");
            Predicate regionPredicate = criteriaBuilder.like(root.<String>get("region"), "%" + search + "%");

            return criteriaBuilder.or(namePredicate, colorPredicate,
                    shinePredicate, transparencyPredicate, fragilityPredicate,
                    opalescencePredicate, regionPredicate);
        };
    }

    public static Specification<Mineral> byName(String mineralName) {
        return (root, query, criteriaBuilder) -> {
            if (mineralName == "" || mineralName == null) {
                return criteriaBuilder.conjunction();
            }
            return root.<String>get("name").in(mineralName);
        };
    }

    public static Specification<Mineral> byColor(String mineralColor) {
        return (root, query, criteriaBuilder) -> {
            if (mineralColor == "" || mineralColor == null) {
                return criteriaBuilder.conjunction();
            }
            return root.<String>get("color").in(mineralColor);
        };
    }

    public static Specification<Mineral> byShine(String mineralShine) {
        return (root, query, criteriaBuilder) -> {
            if (mineralShine == "" || mineralShine == null) {
                return criteriaBuilder.conjunction();
            }
            return root.<String>get("shine").in(mineralShine);
        };
    }

    public static Specification<Mineral> byTransparency(String mineralTransparency) {
        return (root, query, criteriaBuilder) -> {
            if (mineralTransparency == "" || mineralTransparency == null) {
                return criteriaBuilder.conjunction();
            }
            return root.<String>get("transparency").in(mineralTransparency);
        };
    }

    public static Specification<Mineral> byFragility(String mineralFragility) {
        return (root, query, criteriaBuilder) -> {
            if (mineralFragility == "" || mineralFragility == null) {
                return criteriaBuilder.conjunction();
            }
            return root.<String>get("fragility").in(mineralFragility);
        };
    }

    public static Specification<Mineral> byOpalescence(Character mineralOpalescence) {
        return (root, query, criteriaBuilder) -> {
            if (mineralOpalescence == null) {
                return criteriaBuilder.conjunction();
            }
            return root.<Character>get("opalescence").in(mineralOpalescence);
        };
    }

    public static Specification<Mineral> byRegion(String mineralRegion) {
        return (root, query, criteriaBuilder) -> {
            if (mineralRegion == "" || mineralRegion == null) {
                return criteriaBuilder.conjunction();
            }
            return root.<String>get("region").in(mineralRegion);
        };
    }

    public Specification<Mineral> findByFilters(String name, String color, String shine, String transparency, String fragility,
                                                Character opalescence, String region){
        return byName(name)
                .and(byColor(color))
                .and(byShine(shine))
                .and(byTransparency(transparency))
                .and(byFragility(fragility))
                .and(byOpalescence(opalescence))
                .and(byRegion(region));

    }
}