package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        String accountNumber = user.getAccount().getNumber();
        if (userRepository.existsByAccount_Number(accountNumber)) {
            throw new IllegalArgumentException("User with account number " + accountNumber + " already exists");
        }
        return userRepository.save(user);
    }
}
