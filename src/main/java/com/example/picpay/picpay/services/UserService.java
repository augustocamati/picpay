package com.example.picpay.picpay.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.picpay.picpay.domain.user.User;
import com.example.picpay.picpay.domain.user.UserType;
import com.example.picpay.picpay.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
   private UserRepository userRepository;

   public void validateTransaction(User sender, BigDecimal amount) throws Exception {
       if (sender.getBalance().compareTo(amount) < 0) {
           throw new Exception("Saldo insuficiente");
       }

       if(sender.getUserType() == UserType.MERCHANT) {
           throw new Exception("Usuário não pode realizar transações");
       }

   }

   public User findUserById(Long id) throws Exception {
       return userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
   }

   public User CreateUser(User user) {
       return userRepository.save(user);
   } 
}
