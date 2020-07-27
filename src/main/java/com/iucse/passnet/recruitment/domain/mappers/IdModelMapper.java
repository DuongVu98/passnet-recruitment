package com.iucse.passnet.recruitment.domain.mappers;

import java.util.List;

public interface IdModelMapper<T, ID> {
    T mapIdToEntity(ID id);
    List<T> mapIdListToEntityList(List<ID> idList);
    ID mapToId(T t);
    List<ID> mapToIdList(List<T> tList);
}
