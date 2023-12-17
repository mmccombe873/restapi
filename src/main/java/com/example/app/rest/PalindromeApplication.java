package com.example.app.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Initiates and runs the Spring Boot application.
 *
 * Spring Boot was used to build this web application. The app takes a request via the header in the format
 * http://localhost:8080/username/data. It then creates a GET request in the PalindromeController class to
 * extract the username and data. A PalindromeController class is used to set up routes. This class also
 * validates the input and rejects inputs with spaces, numbers, or punctuation, and checks if the input
 * data is already stored in the cache by reading from the results.txt file. Upon startup, the cache is
 * populated with the data from this file in the CacheInitialiser class. If the input data is already in
 * the file, the app gets the stored response from the cache. If the input is new, the app calls the
 * checkPalindrome method in the PalindromeService class which reverses the string and compares it to the
 * original string. The response also has a boolean named palindrome to indicate whether the input is a
 * palindrome or not. I assumed that repeat values should not be added to the cache even if the username
 * is different, so that the program will still retrieve the existing response from the cache. I also moved
 * the input validation class to the Controller class so that the The logs
 * will display the user who originally added the data to the cache.
 *
 * The app architecture follows the OOP paradigm, with object classes being used to create request and
 * response objects. This improves modularity, allows this code to be reused, and makes testing these
 * classes easier. Unit testing has been included in the test folder to show correct functionality of
 * the application.
 */
@SpringBootApplication
public class PalindromeApplication {
	// Initiates and runs the Spring Boot application.
	public static void main(String[] args) {
		SpringApplication.run(PalindromeApplication.class, args);
	}

}
