package com.iucse.passnet.recruitment.domain.mappers;

import java.util.List;

public interface DtoModelMapper<Entity, Dto> {
    Entity mapToEntity(Dto dto);
    List<Entity> mapToEntityList(List<Dto> dtoList);
    Dto mapToDto(Entity e);
    List<Dto> mapToDtoList(List<Entity> eList);
}
