package com.desafio.palindromos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.palindromos.model.PalindromeRequest;

public interface RequestRepository extends JpaRepository<PalindromeRequest, Long>{

}
