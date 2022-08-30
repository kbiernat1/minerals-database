package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.mapper.MineralMapper;
import com.crud.minerals.repository.MineralsRepository;
import com.crud.minerals.service.DbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(MineralSearchController.class)
public class MineralSearchControllerTest {

    @MockBean
    private MineralsRepository mineralsRepository;

    @MockBean
    private DbService dbService;

    @MockBean
    private MineralMapper mineralMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getSelectedMineral() throws Exception {
        //given
        Mineral mineral1 = new Mineral(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");

        dbService.saveMineral(mineral1);
        //when

        List<Mineral> list2 = dbService.retrieveByDifferentParameters("labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");

        Assertions.assertEquals(1, list2.size());
    }
}
