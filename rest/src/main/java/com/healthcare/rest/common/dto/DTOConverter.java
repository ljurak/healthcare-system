package com.healthcare.rest.common.dto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic interface exposing methods for converting
 * from/to DTOs or entities. 
 *
 * @param <D> DTO
 * @param <E> entity
 */
public interface DTOConverter<D, E> {
	
	E convertFromDTO(D dto);
	
	D convertFromEntity(E entity);
	
	default List<E> convertFromDTO(List<D> dtoList) {
		return dtoList
				.stream()
				.map(this::convertFromDTO)
				.collect(Collectors.toList());
	}
	
	default List<D> convertFromEntity(List<E> entityList) {
		return entityList
				.stream()
				.map(this::convertFromEntity)
				.collect(Collectors.toList());
	}
}
