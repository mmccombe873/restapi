package com.example.app.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Initiates and runs the Spring Boot application.
 *
 * Spring Boot was used to build this web application. The app takes a request via the header in the format
 * http://localhost:8080/username/data. It then creates a GET request in the PalindromeController class to
 * extract both parameters. The app checks if the input data is already stored in the cache by reading from
 * the results.txt file. Upon startup, the cache is populated with the data from this file in the
 * CacheInitialiser class. If the input data is already in the file, the app gets the stored response from
 * the cache. If the input is new, the app calls the checkPalindrome method in the PalindromeService class
 * which reverses the string and compares it to the original string. This class includes validation to
 * return an error message if the input is null or contains spaces, numbers, or punctuation. The JSON
 * response will display an error message if the input is invalid. The response also has a boolean named
 * palindrome to indicate whether the input is a palindrome or not.
 *
 * Testing has been included in the test folder to show correct functionality of the application.
 */
@SpringBootApplication
public class PalindromeApplication {
	// Initiates and runs the Spring Boot application.
	public static void main(String[] args) {
		SpringApplication.run(PalindromeApplication.class, args);
	}

}
