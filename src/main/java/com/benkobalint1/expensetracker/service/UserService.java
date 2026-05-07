package com.benkobalint1.expensetracker.service;

import com.benkobalint1.expensetracker.domain.User;
import com.benkobalint1.expensetracker.dto.RegisterRequestDto;

/**
 * @author benkobalint1
 **/
public interface UserService {
    User register(RegisterRequestDto request);
}
