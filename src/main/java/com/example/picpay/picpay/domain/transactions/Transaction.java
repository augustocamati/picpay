package com.example.picpay.picpay.domain.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.picpay.picpay.domain.user.User;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
@Entity(name = "transactions")
public class Transaction {

  


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal amount;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User sender;
  
  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private User receiver;
  

  private LocalDateTime timestamp;

  public Transaction(User sender, User receiver, BigDecimal amount) {
    this.sender = sender;
    this.receiver = receiver;
    this.amount = amount;
    this.timestamp = LocalDateTime.now();
  }
}
