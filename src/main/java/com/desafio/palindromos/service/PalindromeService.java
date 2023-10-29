package com.desafio.palindromos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desafio.palindromos.dto.PalindromeRequestDTO;
import com.desafio.palindromos.dto.mapper.PalindromeRequestMapper;
import com.desafio.palindromos.exception.PalindromeNotFoundException;
import com.desafio.palindromos.model.Palindrome;
import com.desafio.palindromos.model.PalindromeRequest;
import com.desafio.palindromos.repository.PalindromeRepository;
import com.desafio.palindromos.repository.RequestRepository;
import com.desafio.palindromos.utils.PalindromeUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PalindromeService {
	private RequestRepository requestRepository;
	private PalindromeRepository palindromeRepository;
	private PalindromeRequestMapper palindromeRequestMapper;
	private PalindromeUtil palindromeUtil;

	public PalindromeRequestDTO savePalindomes(String matrix) {
		List<Palindrome> allPalindromes = this.palindromeUtil.getAllPalindromesFromMatrix(matrix);

		if(!allPalindromes.isEmpty()) {
			PalindromeRequest request = new PalindromeRequest();
			
			allPalindromes.stream().forEach(palindrome -> palindrome.setRequest(request));
			request.setPalindromes(allPalindromes);
			
			return this.palindromeRequestMapper.toDTO(this.requestRepository.save(request));
		}
		else {
			throw new PalindromeNotFoundException();
		}
		
	}

	public List<PalindromeRequestDTO> findByWord(String palindromeWord){

		  List<PalindromeRequestDTO> list = this.palindromeRepository
				.findByWord(palindromeWord)
				.stream()
				.map(palindromeEntity -> this.palindromeRequestMapper.toDTO(palindromeEntity.getRequest()))
				.toList();
				
		  if(!list.isEmpty()) {
			  return list;
		  }
		  else {
			  throw new PalindromeNotFoundException(palindromeWord);
		  }
		  
	}
}
