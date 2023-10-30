package com.desafio.palindromos.controller;

import static com.desafio.palindromos.PalindromeTestsUtil.INVALID_MATRIX;
import static com.desafio.palindromos.PalindromeTestsUtil.MATRIX_WITHOUT_3ROWS_OR_3COLUMNS;
import static com.desafio.palindromos.PalindromeTestsUtil.MATRIX_WITH_EMPTY_CHAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.desafio.palindromos.dto.PalindromeRequestDTO;
import com.desafio.palindromos.exception.InvalidMatrixException;
import com.desafio.palindromos.service.PalindromeService;
import com.desafio.palindromos.utils.Constants;

@ExtendWith(MockitoExtension.class)
class PalindromeControllerTest{
    @InjectMocks
    PalindromeController controller;
    
    @Mock
    PalindromeService service;
    
    @Test
    void saveMatrixWithPalindromesShouldReturnPalindromeRequestDTO(){
    	PalindromeRequestDTO palindromeRequestDTO = new PalindromeRequestDTO(1l, List.of("aaa","bbb"));
		String validMatrix = "a a a b b b";
    	
        
        when(this.service.savePalindomes(validMatrix)).thenReturn(palindromeRequestDTO);
        
        ResponseEntity<PalindromeRequestDTO> responseEntity = this.controller.savePalindomes(validMatrix);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        
        PalindromeRequestDTO vResult = responseEntity.getBody();
		assertEquals(1l, vResult.id());
		assertEquals(2, vResult.palindrimes().size());
		assertTrue(vResult.palindrimes().contains("aaa"));
		assertTrue(vResult.palindrimes().contains("bbb"));
    }

    @ParameterizedTest
	@ValueSource(strings = {INVALID_MATRIX, MATRIX_WITH_EMPTY_CHAR, MATRIX_WITHOUT_3ROWS_OR_3COLUMNS, "", " "})
	void saveInvalidPalindromeMatrix_thenShouldThrowInvalidMatrixException(String matrix) throws Exception {
    	Exception exception = assertThrows(InvalidMatrixException.class, () -> {
			controller.savePalindomes(matrix);
		});

		assertEquals(Constants.ERROR_MSG_INVALID_MATRIX, exception.getMessage());
	}
    
    @Test
	void findByWordStoredInDBShouldReturnPalindromeRequestDTO() {
		PalindromeRequestDTO palindromeRequestDTO_1 = new PalindromeRequestDTO(1l, List.of("aaa","bbb"));
		PalindromeRequestDTO palindromeRequestDTO_2 = new PalindromeRequestDTO(2l, List.of("aaa","ddd"));
		List<PalindromeRequestDTO> palindromeRequestList = List.of(palindromeRequestDTO_1, palindromeRequestDTO_2);
		
		String word = "aaa";

		when(service.findByWord(word)).thenReturn(palindromeRequestList );
		this.controller = new PalindromeController(service);

		ResponseEntity<List<PalindromeRequestDTO>> responseEntity = this.controller.findByWord(word);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        
		 List<PalindromeRequestDTO> vResult = responseEntity.getBody();

		assertEquals(2, vResult.size());

		assertEquals(1l, vResult.get(0).id());
		assertEquals("aaa", vResult.get(0).palindrimes().get(0));
		assertEquals("bbb", vResult.get(0).palindrimes().get(1));
		
		assertEquals(2l, vResult.get(1).id());
		assertEquals("aaa", vResult.get(1).palindrimes().get(0));
		assertEquals("ddd", vResult.get(1).palindrimes().get(1));
	}
}