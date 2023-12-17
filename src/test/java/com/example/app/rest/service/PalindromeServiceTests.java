package com.example.app.rest.service;

import com.example.app.rest.models.PalindromeRequest;
import com.example.app.rest.models.PalindromeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test file for Palindrome Service. Contains unit tests for checkPalindrome and validateInput.
 */
@SpringBootTest
public class PalindromeServiceTests {

    private final PalindromeService palindromeService = new PalindromeService();
    private final PalindromeRequest request = new PalindromeRequest();

    @Test
    public void checkPalindromeValid() {
        request.setUsername("testUser");
        request.setData("racecar");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertTrue(response.isPalindrome());
        assertEquals(200, response.getStatusCode());
        assertNull(response.getError());
    }

    @Test
    public void checkNonPalindromeValid() {
        request.setUsername("testUser");
        request.setData("hello");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(200, response.getStatusCode());
        assertNull(response.getError());
    }

    @ParameterizedTest
    @CsvSource({
            "validUsername, validData, ",
            ", radar, Username and data must not be empty.",
            "user123, radar, Input must not contain digits.",
            "user with space, radar, Input must not contain spaces.",
            "user!@#, radar, Input must not contain punctuation.",
            "testUser, , Username and data must not be empty.",
            "testUser, 12321, Input must not contain digits.",
            "testUser, race car, Input must not contain spaces.",
            "testUser, race'car, Input must not contain punctuation."
    })

    public void testInputValidation(String username, String data, String expectedError) {
        String result = palindromeService.validateInput(username, data);
        assertEquals(expectedError, result, "Validation error does not match expected result");
    }

}

