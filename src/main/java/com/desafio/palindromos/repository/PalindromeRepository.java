package com.desafio.palindromos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.palindromos.model.Palindrome;

public interface PalindromeRepository extends JpaRepository<Palindrome, Long>{

	List<Palindrome> findByWord(String word);

}
