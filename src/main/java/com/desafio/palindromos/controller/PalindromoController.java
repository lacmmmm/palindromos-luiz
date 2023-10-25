package com.desafio.palindromos.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/palindromos")
@AllArgsConstructor
public class PalindromoController {
	
	@GetMapping("/hello")
	public String hello() {

		return "Hello";
	}
}
