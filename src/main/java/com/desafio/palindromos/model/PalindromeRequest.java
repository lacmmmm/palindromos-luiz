package com.desafio.palindromos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "palindrome_request")
@Data
public class PalindromeRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	@JsonProperty("_id")
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "request")
	private List<Palindrome> palindromes;
}
