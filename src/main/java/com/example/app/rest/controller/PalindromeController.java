package com.example.app.rest.controller;

import com.example.app.rest.models.PalindromeRequest;
import com.example.app.rest.models.PalindromeResponse;
import com.example.app.rest.service.PalindromeService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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

    @Autowired
    private PalindromeService palindromeService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        System.out.println("Welcome page accessed.");
        return "Welcome! Please append a username and data in the URL in the format /username/data to generate a response.";
    }

    @GetMapping("/{username}/{data}")
    @ResponseBody
    public PalindromeResponse getPalindrome(@PathVariable String username, @PathVariable String data) {
        PalindromeRequest request = new PalindromeRequest();
        request.setUsername(username);
        request.setData(data);

        // Check if result is already stored
        Pair<String, Boolean> cachedResult = readFromFile(data);
        System.out.println("Checking cache for " + data);

        if (cachedResult != null) {
            // Result is already stored in cache
            boolean isPalindrome = cachedResult.getRight();
            String storedUsername = cachedResult.getLeft();
            System.out.println("Result already stored in cache by " + storedUsername + ": " + data + "," + isPalindrome);
            return new PalindromeResponse(username, data, isPalindrome, 200);
        } else {
            // Perform the palindrome check
            PalindromeResponse response = palindromeService.checkPalindrome(request);
            // Store the result, including the boolean value
            if(response.getStatusCode() == 200) {
                storeResultToFile(username, data, response.isPalindrome());
            } else {
                System.out.println("Input error. Result not stored.");
            }
            return response;
        }
    }

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


    private Pair<String, Boolean> readFromFile(String data) {
        try {
            String filePath = "results.txt";
            String expectedData = "," + data + ",";

            return Files.lines(Path.of(filePath))
                    .filter(line -> line.contains(expectedData))
                    .findFirst()
                    .map(line -> {
                        String[] parts = line.split(",");
                        String storedUsername = parts[0];
                        boolean isPalindrome = Boolean.parseBoolean(parts[2]);
                        return new ImmutablePair<>(storedUsername, isPalindrome);
                    })
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading result from file.");
            return null;
        }
    }
}
