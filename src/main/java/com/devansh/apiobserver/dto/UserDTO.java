package com.devansh.apiobserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private List<String> services;
    private String password;
}
