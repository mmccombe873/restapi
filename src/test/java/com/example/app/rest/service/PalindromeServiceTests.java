package com.example.app.rest.service;

import com.example.app.rest.models.PalindromeRequest;
import com.example.app.rest.models.PalindromeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void checkNullPalindromeInvalid() {
        request.setUsername("testUser");
        request.setData(null);

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void checkPalindromeWithSpaceInvalid() {
        request.setUsername("testUser");
        request.setData("race car");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void checkPalindromeWithNumberInvalid() {
        request.setUsername("testUser");
        request.setData("12321");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void checkPalindromeWithPunctuationInvalid() {
        request.setUsername("testUser");
        request.setData("race,car");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void checkNullUsernameInvalid() {
        request.setUsername(null);
        request.setData("civic");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void checkUsernameWithSpaceInvalid() {
        request.setUsername("test User");
        request.setData("civic");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void checkUsernameWithNumberInvalid() {
        request.setUsername("testUser123");
        request.setData("civic");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void checkUsernameWithPunctuationInvalid() {
        request.setUsername("test,User");
        request.setData("civic");

        PalindromeResponse response = palindromeService.checkPalindrome(request);

        assertFalse(response.isPalindrome());
        assertEquals(400, response.getStatusCode());
        assertNotNull(response.getError());
    }

    @Test
    public void testValidInput() {
        String result = palindromeService.validateInput("validUsername", "validData");
        assertNull(result, "Input should be valid");
    }

    @Test
    public void testNullUsername() {
        String result = palindromeService.validateInput(null, "radar");
        assertEquals("Username and data must not be empty.", result, "Null username and data should produce an error");
    }

    @Test
    public void testUsernameWithDigits() {
        String result = palindromeService.validateInput("user123", "radar");
        assertEquals("Input must not contain digits.", result, "Input with digits should produce an error");
    }

    @Test
    public void testUsernameWithSpaces() {
        String result = palindromeService.validateInput("user with space", "radar");
        assertEquals("Input must not contain spaces.", result, "Input with spaces should produce an error");
    }

    @Test
    public void testUsernameWithPunctuation() {
        String result = palindromeService.validateInput("user!@#", "radar");
        assertEquals("Input must not contain punctuation.", result, "Input with punctuation should produce an error");
    }

    @Test
    public void testNullData() {
        String result = palindromeService.validateInput("testUser", null);
        assertEquals("Username and data must not be empty.", result, "Null username and data should produce an error");
    }

    @Test
    public void testInputWithDigits() {
        String result = palindromeService.validateInput("testUser", "12321");
        assertEquals("Input must not contain digits.", result, "Input with digits should produce an error");
    }

    @Test
    public void testInputWithSpaces() {
        String result = palindromeService.validateInput("testUser", "race car");
        assertEquals("Input must not contain spaces.", result, "Input with spaces should produce an error");
    }

    @Test
    public void testInputWithPunctuation() {
        String result = palindromeService.validateInput("testUser", "race,car");
        assertEquals("Input must not contain punctuation.", result, "Input with punctuation should produce an error");
    }
}

