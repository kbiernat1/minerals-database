package com.crud.minerals.controller;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.domain.MineralDto;
import com.crud.minerals.mapper.MineralMapper;
import com.crud.minerals.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AdminController.class)
@WithMockUser(username = "admin", password = "123", roles = "ADMIN")
public class AdminControllerTest {

    @MockBean
    private DbService dbService;

    @MockBean
    private MineralMapper mineralMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnEmptyDtoMineralsList() throws Exception {
        //when
        when(dbService.getAllMinerals()).thenReturn(List.of());

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/adminPanel/minerals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void returnDtoMineralsList() throws Exception {
        //given
        List<Mineral> minerals = Arrays.asList(new Mineral(1L, "labradorite", "blue", "medium", "none",
                "medium", 'Y', "Lower Silesia"), new Mineral(2L, "ruby", "red", "high",
                "high", "medium", 'N', "Upper Silesia"));
        List<MineralDto> mineralDto = Arrays.asList(new MineralDto(1L, "labradorite", "blue", "medium", "none",
                "medium", 'Y', "Lower Silesia"), new MineralDto(2L, "ruby", "red", "high",
                "high", "medium", 'N', "Upper Silesia"));

        //when
        when(dbService.getAllMinerals()).thenReturn(minerals);
        when(mineralMapper.mineralToDtoList(minerals)).thenReturn(mineralDto);

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/adminPanel/minerals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("labradorite")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color", Matchers.is("blue")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].shine", Matchers.is("medium")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transparency", Matchers.is("none")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fragility", Matchers.is("medium")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].opalescence", Matchers.is("Y")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].region", Matchers.is("Lower Silesia")));
    }

    @Test
    void returnSelectedMineral() throws Exception {
        //given
        Mineral mineral = new Mineral(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");
        MineralDto mineralDto = new MineralDto(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");

        //when
        when(mineralMapper.mapToMineralDto(ArgumentMatchers.any(Mineral.class))).thenReturn(mineralDto);
        when(dbService.getMineral(1L)).thenReturn(mineral);

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/adminPanel/minerals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("labradorite")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("blue")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shine", Matchers.is("medium")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transparency", Matchers.is("none")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fragility", Matchers.is("medium")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.opalescence", Matchers.is("Y")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.region", Matchers.is("Lower Silesia")));
    }

    @Test
    void doNotReturnSelectedMineral() throws Exception {
        //when
        when(dbService.getMineral(1L)).thenReturn(null);

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/adminPanel/minerals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createMineral() throws Exception {
        //given
        Mineral mineral = new Mineral(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");
        MineralDto mineralDto = new MineralDto(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");

        //when
        when(mineralMapper.mapToMineral(ArgumentMatchers.any(MineralDto.class))).thenReturn(mineral);

        Gson gson = new Gson();
        String json = gson.toJson(mineralDto);

        //then
        mockMvc.perform(post("/adminPanel/minerals").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void updateMineral() throws Exception {
        //given
        Mineral mineral = new Mineral(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");
        MineralDto mineralDto = new MineralDto(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");

        //when
        when(mineralMapper.mapToMineralDto(ArgumentMatchers.any(Mineral.class))).thenReturn(mineralDto);
        when(dbService.saveMineral(ArgumentMatchers.any(Mineral.class))).thenReturn(mineral);
        when(mineralMapper.mapToMineral(ArgumentMatchers.any(MineralDto.class))).thenReturn(mineral);

        Gson gson = new Gson();
        String json = gson.toJson(mineralDto);

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/adminPanel/minerals").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("labradorite")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("blue")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shine", Matchers.is("medium")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transparency", Matchers.is("none")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fragility", Matchers.is("medium")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.opalescence", Matchers.is("Y")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.region", Matchers.is("Lower Silesia")));

    }

    @Test
    void deleteMineral() throws Exception {
        //given
        Mineral mineral = new Mineral(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");
        when(dbService.getMineral(1L)).thenReturn(mineral);

        //then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/adminPanel/minerals/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
