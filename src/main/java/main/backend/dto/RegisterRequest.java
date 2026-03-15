package main.backend.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String phone;
    private String password;
}
