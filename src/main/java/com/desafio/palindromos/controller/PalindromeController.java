package com.desafio.palindromos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.palindromos.dto.PalindromeRequestDTO;
import com.desafio.palindromos.service.PalindromeService;
import com.desafio.palindromos.utils.Constants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/savePalindomes")
	public PalindromeRequestDTO savePalindomes(
			@RequestBody String matrix) {
		
		return this.palindromeService.savePalindomes(matrix);
	}

	@GetMapping("/{palindrome}")
	public List<PalindromeRequestDTO> findByWord(
			@PathVariable("palindrome") @NotBlank(message = Constants.ERROR_MSG_PALINDROME_NOT_BLANK) @Size(min = 3, message = Constants.ERROR_MSG_PALINDROME_MIN_SIZE)String palindrome){

		return this.palindromeService.findByWord(palindrome);
	}
}
