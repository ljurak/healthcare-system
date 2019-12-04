package com.healthcare.service.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic interface exposing methods for converting
 * from/to DTOs or entities. 
 * 
 * @author ljurak
 *
 * @param <D> DTO
 * @param <E> entity
 */
public interface GenericConverter<D, E> {
	
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
