package com.example.picpay.picpay.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.picpay.picpay.domain.user.User;
import com.example.picpay.picpay.dto.request.CreateUserDto;
import com.example.picpay.picpay.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

@PostMapping
public ResponseEntity<User>  createUser(@RequestBody CreateUserDto createUserDto) {
    User user = userService.creatUser(createUserDto);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
   
}

@GetMapping
public List<User> getAllUsers() {

    List<User> users =  userService.findAllUsers();
    return users;
    

  
}
}