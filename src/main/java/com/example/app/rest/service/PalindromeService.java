package com.example.app.rest.service;

import com.example.app.rest.models.PalindromeRequest;
import com.example.app.rest.models.PalindromeResponse;
import org.springframework.stereotype.Service;

/**
 * Service class containing methods which check whether the input is a palindrome or not and validate inputs.
 */
@Service
public class PalindromeService {
    /**
     * Reverses the input data and checks it against the original input to see if they are the same.
     * Returns an error message along with a 400 status code if the input is not valid.
     * @param request - Request object containing the input username and data.
     */
    public PalindromeResponse checkPalindrome(PalindromeRequest request) {
        // Call getters from request model class
        String username = request.getUsername();
        String data = request.getData();

        // Reverse the string and compare to the original data
        String reversed = new StringBuilder(data).reverse().toString();
        boolean isPalindrome = data.equals(reversed);
        // Return a response object showing whether the input data is a palindrome or not.
        if (isPalindrome) {
            return new PalindromeResponse(username, data,true, 200);
        } else {
            return new PalindromeResponse(username, data, false, 200);
        }

    }

    /**
     * Returns a validation error message if the input is invalid, or null if the input is valid.
     * @param username - The username from the GET request
     * @param data - The data from the GET request
     */
    public String validateInput(String username, String data) {
        if (username == null || username.equals("") || data == null || data.equals("")) {
            return "Username and data must not be empty.";
        } else if (username.matches(".*\\d.*") || data.matches(".*\\d.*")) {
            return "Input must not contain digits.";
        } else if (username.matches(".*\\s.*") || data.matches(".*\\s.*")) {
            return "Input must not contain spaces.";
        } else if (username.matches(".*\\p{Punct}.*") || data.matches(".*\\p{Punct}.*")) {
            return "Input must not contain punctuation.";
        } else {
            return null; // Input is valid
        }
    }

}
