package com.iucse.passnet.recruitment.domain.mappers;

public interface ModelMapper<Entity, Dto, Id> extends IdModelMapper<Entity, Id>, DtoModelMapper<Entity, Dto> {
}
