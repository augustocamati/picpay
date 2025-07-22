package com.example.picpay.picpay.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.picpay.picpay.domain.user.User;
import com.example.picpay.picpay.domain.user.UserType;
import com.example.picpay.picpay.dto.request.CreateUserDto;
import com.example.picpay.picpay.exceptions.InsufficientBalanceException;
import com.example.picpay.picpay.exceptions.TransactionNotAllowedForUserTypeException;
import com.example.picpay.picpay.exceptions.UserNotFoundException;
import com.example.picpay.picpay.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        if (sender.getUserType() == UserType.MERCHANT) {
            throw new TransactionNotAllowedForUserTypeException();
        }

    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User creatUser(CreateUserDto createUserDto) {
        User user = new User(createUserDto);
        saveUser(user);

        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
