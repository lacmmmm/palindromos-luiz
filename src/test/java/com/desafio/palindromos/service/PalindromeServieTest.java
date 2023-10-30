package com.desafio.palindromos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafio.palindromos.dto.PalindromeRequestDTO;
import com.desafio.palindromos.dto.mapper.PalindromeRequestMapper;
import com.desafio.palindromos.exception.PalindromeNotFoundException;
import com.desafio.palindromos.helper.ConstantsTest;
import com.desafio.palindromos.helper.PalindromeTestsHelper;
import com.desafio.palindromos.model.Palindrome;
import com.desafio.palindromos.model.PalindromeRequest;
import com.desafio.palindromos.repository.PalindromeRepository;
import com.desafio.palindromos.repository.RequestRepository;

@ExtendWith(MockitoExtension.class)
class PalindromeServieTest{
	@InjectMocks
	PalindromeService service;

	@Mock
	private RequestRepository requestRepository;

	@Mock
	private PalindromeRepository palindromeRepository;

	@Spy
	@InjectMocks
	private PalindromeRequestMapper palindromeRequestMapper = Mappers.getMapper(PalindromeRequestMapper.class);

	@Test
	void saveMatrixWithPalindromesShouldReturnPalindromeRequestDTO(){
		List<String> wordList = List.of("aaa", "bbb");

		PalindromeRequest palindromeRequest = new PalindromeRequest();
		palindromeRequest.setId(1l);
		PalindromeRequest palindromeRequestList = PalindromeTestsHelper.getPalindromeRequestEntity(wordList, palindromeRequest);

		String validMatrix = "a a a b b b";

		when(this.requestRepository.save(any(PalindromeRequest.class))).thenReturn(palindromeRequestList);     
		PalindromeRequestDTO result = this.service.savePalindomes(validMatrix);

		assertEquals(1l, result.id());
		assertEquals(2, result.palindrimes().size());
		assertTrue(result.palindrimes().contains("aaa"));
		assertTrue(result.palindrimes().contains("bbb"));
	}

	@Test
	void saveMatrixWithoutPalindromesShouldThowPalindromeNotFoundException() throws Exception {
		Exception exception = assertThrows(PalindromeNotFoundException.class, () -> {
			this.service.savePalindomes("a a c");
		});

		assertEquals(ConstantsTest.ERROR_MSG_MATRIX_WITHOUT_PALINDROMES, exception.getMessage());
	}


	@Test
	void findByWordStoredInDBShouldReturnPalindromeRequestDTO(){
		List<String> wordList1 = List.of("aaa", "bbb", "ccc");
		List<String> wordList2 = List.of("aaa", "111", "222", "333");

		PalindromeRequest palindromeRequest1 = new PalindromeRequest();
		palindromeRequest1.setId(1l);
		palindromeRequest1 = PalindromeTestsHelper.getPalindromeRequestEntity(wordList1, palindromeRequest1);

		PalindromeRequest palindromeRequest2 = new PalindromeRequest();
		palindromeRequest2.setId(2l);
		palindromeRequest2 = PalindromeTestsHelper.getPalindromeRequestEntity(wordList2, palindromeRequest2);

		List<Palindrome> palindromeRequestList = List.of(palindromeRequest1.getPalindromes().get(0), palindromeRequest2.getPalindromes().get(0));

		String word = "aaa";
		when(this.palindromeRepository.findByWord(word)).thenReturn(palindromeRequestList);     

		List<PalindromeRequestDTO> result = this.service.findByWord(word);

		assertEquals(2, result.size());
		
		assertEquals(1l, result.get(0).id());
		assertEquals(3, result.get(0).palindrimes().size());
		assertEquals("aaa", result.get(0).palindrimes().get(0));
		assertEquals("bbb", result.get(0).palindrimes().get(1));
		assertEquals("ccc", result.get(0).palindrimes().get(2));
		
		assertEquals(2l, result.get(1).id());
		assertEquals(4, result.get(1).palindrimes().size());
		assertEquals("aaa", result.get(1).palindrimes().get(0));
		assertEquals("111", result.get(1).palindrimes().get(1));
		assertEquals("222", result.get(1).palindrimes().get(2));
		assertEquals("333", result.get(1).palindrimes().get(3));
	}
	
	@Test
	void findByWordNotStoredInDBShouldThowPalindromeNotFoundException() throws Exception {
		String word = "xxx";
		Exception exception = assertThrows(PalindromeNotFoundException.class, () -> {
			this.service.findByWord(word);
		});

		final String expectedMsg = ConstantsTest.ERROR_MSG_PALINDROME_NOT_FOUND + word;
		assertEquals(expectedMsg, exception.getMessage());
	}
}