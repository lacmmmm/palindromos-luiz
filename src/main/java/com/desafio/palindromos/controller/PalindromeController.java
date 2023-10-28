package com.desafio.palindromos.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.palindromos.dto.PalindromeRequestDTO;
import com.desafio.palindromos.service.PalindromeService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/palindromos")
@AllArgsConstructor
public class PalindromeController {
	private PalindromeService palindromeService;

	@GetMapping("/hello")
	public String hello() {

		return "Hello";
	}

	@PostMapping("/findPalindomes")
	public PalindromeRequestDTO findPalindomes(
			@RequestBody @NotBlank @NotNull String matrix) {
		
		return this.palindromeService.findPalindomes(matrix);
	}

	@GetMapping("/{palindrome}")
	public List<PalindromeRequestDTO> findByWord(
			@PathVariable("palindrome") @NotBlank @NotNull String palindrome){

		return this.palindromeService.findByWord(palindrome);
	}
}
