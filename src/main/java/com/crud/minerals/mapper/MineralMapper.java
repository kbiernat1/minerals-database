package com.crud.minerals.mapper;

import com.crud.minerals.domain.Mineral;
import com.crud.minerals.domain.MineralDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MineralMapper {

    public Mineral mapToMineral(final MineralDto mineralDto) {
        return new Mineral(
                mineralDto.getId(),
                mineralDto.getName(),
                mineralDto.getColor(),
                mineralDto.getShine(),
                mineralDto.getTransparency(),
                mineralDto.getFragility(),
                mineralDto.getOpalescence(),
                mineralDto.getRegion()
        );
    }

    public MineralDto mapToMineralDto(final Mineral mineral) {
        return new MineralDto(
                mineral.getId(),
                mineral.getName(),
                mineral.getColor(),
                mineral.getShine(),
                mineral.getTransparency(),
                mineral.getFragility(),
                mineral.getOpalescence(),
                mineral.getRegion()
        );
    }

    public List<MineralDto> mineralToDtoList(final List<Mineral> mineralList) {
        return mineralList.stream()
                .map(this::mapToMineralDto)
                .collect(Collectors.toList());
    }
}
