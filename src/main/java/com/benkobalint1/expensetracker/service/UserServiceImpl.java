package com.benkobalint1.expensetracker.service;

import com.benkobalint1.expensetracker.domain.Account;
import com.benkobalint1.expensetracker.domain.AccountType;
import com.benkobalint1.expensetracker.domain.User;
import com.benkobalint1.expensetracker.dto.RegisterRequestDto;
import com.benkobalint1.expensetracker.repository.AccountRepository;
import com.benkobalint1.expensetracker.repository.UserRepository;
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

    public UserServiceImpl(UserRepository userRepository,
                           AccountRepository accountRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
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
}
