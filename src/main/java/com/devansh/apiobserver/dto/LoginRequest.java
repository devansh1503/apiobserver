package com.devansh.apiobserver.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
