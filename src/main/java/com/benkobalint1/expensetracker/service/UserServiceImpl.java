package com.benkobalint1.expensetracker.service;

import com.benkobalint1.expensetracker.domain.Account;
import com.benkobalint1.expensetracker.domain.AccountType;
import com.benkobalint1.expensetracker.domain.User;
import com.benkobalint1.expensetracker.dto.LoginRequestDto;
import com.benkobalint1.expensetracker.dto.LoginResponseDto;
import com.benkobalint1.expensetracker.dto.RegisterRequestDto;
import com.benkobalint1.expensetracker.exception.UnauthorizedException;
import com.benkobalint1.expensetracker.repository.AccountRepository;
import com.benkobalint1.expensetracker.repository.UserRepository;
import com.benkobalint1.expensetracker.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author benkobalint1
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           AccountRepository accountRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public User register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email address already in use");
        } else {
            User user = new User();
            user.setFullName(request.getFullName());
            user.setEmail(request.getEmail());
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

            User savedUser = userRepository.save(user);

            Account personalAccount = new Account();
            personalAccount.setName(savedUser.getFullName() + "'s account");
            personalAccount.setOwner(savedUser);
            personalAccount.setType(AccountType.PERSONAL);
            accountRepository.save(personalAccount);

            return savedUser;
        }
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());
        return new LoginResponseDto(token, user.getEmail(), user.getId());
    }
}
