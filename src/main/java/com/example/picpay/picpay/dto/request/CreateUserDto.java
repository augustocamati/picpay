package com.example.picpay.picpay.dto.request;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.example.picpay.picpay.domain.user.UserType;

public record CreateUserDto(
  String firstName,
  String lastName,
  BigDecimal balance,
  String email,
  String password,
  String document,
  UserType userType
 
) {
  
}
