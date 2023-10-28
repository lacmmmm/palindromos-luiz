package com.desafio.palindromos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desafio.palindromos.dto.PalindromeRequestDTO;
import com.desafio.palindromos.dto.mapper.PalindromeRequestMapper;
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
	private PalindromeUtil palindromeUtil;
	private PalindromeRequestMapper palindromeRequestMapper;

	public PalindromeRequestDTO findPalindomes(String matrix) {
		List<Palindrome> allPalindromes = this.palindromeUtil.getAllPalindromesFromMatrix(matrix);

		PalindromeRequest request = new PalindromeRequest();

		if(!allPalindromes.isEmpty()) {
			allPalindromes.stream().forEach(palindrome -> palindrome.setRequest(request));
			request.setPalindromes(allPalindromes);
		}

		return this.palindromeRequestMapper.toDTO(this.requestRepository.save(request));
	}

	public List<PalindromeRequestDTO> findByWord(String palindrome){

		return this.palindromeRepository
				.findByWord(palindrome)
				.stream()
				.map(p -> this.palindromeRequestMapper.toDTO(p.getRequest()) )
				.toList();
	}
}
