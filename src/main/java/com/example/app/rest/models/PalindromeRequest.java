package com.example.app.rest.models;

/**
 * Data object for requests sent to the application via the header.
 */
public class PalindromeRequest {

    /**
     * The username entered in the header in the format localhost:8080/username/data
     */
    private String username;

    /**
     * The data entered in the header in the format localhost:8080/username/data
     */
    private String data;

    public String getUsername() {
        return username;
    }

    /**
     * Called to set the username in the request.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    /**
     * Called to set the data in the request.
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }
}
