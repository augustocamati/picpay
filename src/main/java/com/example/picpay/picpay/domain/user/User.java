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

  public void credit(BigDecimal amount) {
    this.balance = this.balance.add(amount);
  }

  public void debit(BigDecimal amount) {
    this.balance = this.balance.subtract(amount);
  }

  public boolean isMerchant() {
    return this.userType == UserType.MERCHANT;
  }

  public boolean isBalancerEqualOrGreatherThan(BigDecimal value) {
    return this.balance.doubleValue() >= value.doubleValue();
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", document='" + document + '\'' +
        ", email='" + email + '\'' +
        ", balance=" + balance +
        ", userType=" + userType +
        '}';
  }
}
