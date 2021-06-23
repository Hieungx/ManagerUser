package com.example.demo.mapper;

import java.util.List;

public interface BaseMapper<D, E> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toListEntity(List<D> dtoList);

    List<D> toListDTO(List<E> entityList);
}
