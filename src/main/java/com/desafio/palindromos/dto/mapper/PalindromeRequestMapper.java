package com.desafio.palindromos.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.desafio.palindromos.dto.PalindromeRequestDTO;
import com.desafio.palindromos.model.PalindromeRequest;

@Mapper(componentModel = "spring")
public interface PalindromeRequestMapper{

	@Mapping(target = "palindrimes", expression = "java(entity.getPalindromes().stream().map(p -> p.getWord()).toList())")
	PalindromeRequestDTO toDTO(PalindromeRequest entity);

}
