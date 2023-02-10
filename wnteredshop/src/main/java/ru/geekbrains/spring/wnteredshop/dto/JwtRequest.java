package ru.geekbrains.spring.wnteredshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequest {
    private String userName;
    private String password;
}
