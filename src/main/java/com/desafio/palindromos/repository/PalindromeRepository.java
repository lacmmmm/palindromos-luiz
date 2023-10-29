package com.desafio.palindromos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.palindromos.model.Palindrome;

public interface PalindromeRepository extends JpaRepository<Palindrome, Long>{

	Optional<List<Palindrome>> findByWord(String word);

}
