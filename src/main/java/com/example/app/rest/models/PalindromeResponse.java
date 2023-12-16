package com.example.app.rest.models;

/**
 * Data object for JSON response returned by the application.
 */
public class PalindromeResponse {
    private final String username;
    private final String data;
    private final boolean isPalindrome;
    private final int statusCode;

    private final String error;

    /**
     * @param username
     * @param data
     * @param isPalindrome
     * @param statusCode
     */
    public PalindromeResponse(String username, String data, boolean isPalindrome, int statusCode) {
        this.username = username;
        this.data = data;
        this.isPalindrome = isPalindrome;
        this.statusCode = statusCode;
        this.error = null; // No error for successful responses
    }

    public PalindromeResponse(String error, int statusCode) {
        this.username = null;
        this.data = null;
        this.isPalindrome = false; // Default value for boolean
        this.statusCode = statusCode;
        this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public String getData() {
        return data;
    }

    public boolean isPalindrome() {
        return isPalindrome;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }
}


