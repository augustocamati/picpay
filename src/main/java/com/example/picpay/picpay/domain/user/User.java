package com.example.picpay.picpay.domain.user;

import java.math.BigDecimal;

import com.example.picpay.picpay.dto.request.CreateUserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String firstName;
  private String lastName;

  @Column(unique = true)
  private String document;

  @Column(unique = true)
  private String email;

  private String password;
  private BigDecimal balance;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  public User(CreateUserDto createUserDto) {

    this.firstName = createUserDto.firstName();
    this.lastName = createUserDto.lastName();
    this.email = createUserDto.email();
    this.password = createUserDto.password();
    this.balance = createUserDto.balance();
    this.document = createUserDto.document();

    this.userType = createUserDto.userType();
}

}
