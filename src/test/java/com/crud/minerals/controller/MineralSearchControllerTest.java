package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.repository.MineralsRepository;
import com.crud.minerals.service.DbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MineralSearchControllerTest {

    @Autowired
    MineralsRepository mineralsRepository;
    @Autowired
    DbService dbService;

    @Test
    void retrieveMineralByColorTest() {
        //given
        Mineral mineral1 = new Mineral(2L, "testname1", "green", "high", "high", "medium", 'N', "somewhere");
        Mineral mineral2 = new Mineral(3L, "testname2", "green", "medium", "low", "low", 'Y', "somewhere");
        Mineral mineral3 = new Mineral(4L, "testname3", "blue", "low", "medium", "low", 'N', "somewhere");

        dbService.saveMineral(mineral1);
        dbService.saveMineral(mineral2);
        dbService.saveMineral(mineral3);

        //when
        List<Mineral> mineralsByColorGreen = dbService.retrieveMineralsByColor("green");

        //then
        Assertions.assertEquals(2, mineralsByColorGreen.size());
        //mineralsRepository.deleteAll();
    }
}
