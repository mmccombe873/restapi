package com.example.app.rest.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PalindromeResponseTests {

    @Test
    public void palindromeResponseConstructorValid() {
        String username = "testUser";
        String data = "testData";
        boolean isPalindrome = true;
        int statusCode = 200;

        PalindromeResponse response = new PalindromeResponse(username, data, isPalindrome, statusCode);

        assertEquals(username, response.getUsername());
        assertEquals(data, response.getData());
        assertEquals(isPalindrome, response.isPalindrome());
        assertEquals(statusCode, response.getStatusCode());
        assertNull(response.getError());
    }

    @Test
    public void palindromeResponseConstructorInvalid() {
        String error = "Invalid input";
        int statusCode = 400;

        PalindromeResponse response = new PalindromeResponse(error, statusCode);

        assertNull(response.getUsername());
        assertNull(response.getData());
        assertFalse(response.isPalindrome());
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(error, response.getError());
    }

}
