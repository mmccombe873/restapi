package com.example.app.rest.controller;

import com.example.app.rest.models.PalindromeRequest;
import com.example.app.rest.models.PalindromeResponse;
import com.example.app.rest.service.PalindromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@RestController
public class PalindromeController {

    // Dependency injection, instantiates a palindromeService bean
    @Autowired
    private PalindromeService palindromeService;

    // Dependency injection, instantiates a cacheManager bean
    @Autowired
    private CacheManager cacheManager;

    /**
     * Mapping for default path
     * @return
     */
    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        System.out.println("Welcome page accessed.");
        return "Welcome! Please append a username and data in the URL in the format /username/data to generate a response.";
    }

    /**
     * Mapping for URL with inputs appended. Gets username and palindrome from URL.
     * @param username
     * @param data
     * @return
     */
    @GetMapping("/{username}/{data}")
    @ResponseBody
    public PalindromeResponse getPalindrome(@PathVariable String username, @PathVariable String data) {
        PalindromeRequest request = new PalindromeRequest();
        request.setUsername(username);
        request.setData(data);

        String validationError = palindromeService.validateInput(username, data);

        if (validationError != null) {
            System.out.println("Invalid username or data. Result not returned.");
            return new PalindromeResponse(validationError, 400);
        }

        // Read from the cache
        String cacheKey = '/' + data;
        PalindromeResponse cachedResponse = cacheManager.getCache("palindromeCache").get(cacheKey, PalindromeResponse.class);

        if (cachedResponse != null) {
            // Result is already stored in cache, show user who stored it in log output.
            System.out.println("Result already stored in cache by " + cachedResponse.getUsername() + ": " + data + "," + cachedResponse.isPalindrome());
            return cachedResponse;
        } else {
            // Perform the palindrome check
            PalindromeResponse response = palindromeService.checkPalindrome(request);

            // Store valid inputs including the palindrome check in the cache
            if (response.getStatusCode() == 200) {
                cacheManager.getCache("palindromeCache").put(cacheKey, response);
                storeResultToFile(username, data, response.isPalindrome());
            } else {
                System.out.println("Input error. Result not stored.");
            }
            return response;
        }
    }

    /**
     * Stores the username, data, and palindrome boolean in a text file.
     * @param username
     * @param data
     * @param isPalindrome
     */
    private void storeResultToFile(String username, String data, boolean isPalindrome) {
        try {
            String filePath = "results.txt";
            String entry = String.format("%s,%s,%b%n", username, data, isPalindrome);
            Path path = Path.of(filePath);

            // Check if data is already present in the file
            boolean dataAlreadyPresent = Files.lines(path)
                    .anyMatch(line -> line.contains("," + data + ","));

            if (!dataAlreadyPresent) {
                // Data not found, add a new line
                Files.write(path, entry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                System.out.println("Result added to cache by " + username + ": " + data + "," + isPalindrome);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error storing result to file.");
        }
    }
}
