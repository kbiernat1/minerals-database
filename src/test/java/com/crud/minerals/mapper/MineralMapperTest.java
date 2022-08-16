package com.crud.minerals.mapper;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.domain.MineralDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MineralMapperTest {

    @InjectMocks
    MineralMapper mineralMapper;

    @Test
    void mapToMineralTest() {
        //given
        MineralDto mineralDto = new MineralDto(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");

        //when
        Mineral mineral = mineralMapper.mapToMineral(mineralDto);

        //then
        Assertions.assertEquals(mineralDto.getName(), mineral.getName());
    }

    @Test
    void mapToMineralDtoTest() {
        //given
        Mineral mineral = new Mineral(1L, "labradorite", "blue", "medium", "none", "medium", 'Y', "Lower Silesia");

        //when
        MineralDto mineralDto = mineralMapper.mapToMineralDto(mineral);

        //then
        Assertions.assertEquals(mineral.getName(), mineralDto.getName());
    }

    @Test
    void mineralToDtoListTest() {
        //given
        List<Mineral> minerals = Arrays.asList(new Mineral(1L, "labradorite", "blue", "medium", "none",
                "medium", 'Y', "Lower Silesia"), new Mineral(2L, "ruby", "red", "high",
                "high", "medium", 'N', "Upper Silesia"));
        //when
        List<MineralDto> mineralsDto = mineralMapper.mineralToDtoList(minerals);

        //then
        Assertions.assertEquals(minerals.get(1).getColor(), mineralsDto.get(1).getColor());
    }
}
