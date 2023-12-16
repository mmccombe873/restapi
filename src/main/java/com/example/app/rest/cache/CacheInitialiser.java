package com.example.app.rest.cache;

import com.example.app.rest.models.PalindromeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class CacheInitialiser {

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void initialiseCache() {
        try {
            List<String> lines = Files.readAllLines(Path.of("results.txt"));

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0];
                    String data = parts[1];
                    boolean isPalindrome = Boolean.parseBoolean(parts[2]);
                    String key = username + '/' + data;

                    // Create a PalindromeResponse and put it into the cache
                    PalindromeResponse response = new PalindromeResponse(username, data, isPalindrome, 200);
                    cacheManager.getCache("palindromeCache").put(key, response);
                    System.out.println("Cache has been populated with existing data.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error initialising cache.");
        }
    }
}


