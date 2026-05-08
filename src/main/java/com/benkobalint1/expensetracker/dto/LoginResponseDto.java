package com.benkobalint1.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author benkobalint1
 **/
@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private String email;
    private Long userId;
}
