package com.example.demo.dto;

public class AuthRequestDto {

    private String email;
    private String password;

    public AuthRequestDto() {}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
