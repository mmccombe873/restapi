package com.example.app.rest.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PalindromeRequestTests {

    @Test
    public void getUsernameValid() {
        PalindromeRequest request = new PalindromeRequest();
        String expectedUsername = "testUser";

        request.setUsername(expectedUsername);
        String actualUsername = request.getUsername();

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void getDataValid() {
        // Arrange
        PalindromeRequest request = new PalindromeRequest();
        String expectedData = "testData";

        // Act
        request.setData(expectedData);
        String actualData = request.getData();

        // Assert
        assertEquals(expectedData, actualData);
    }

}
