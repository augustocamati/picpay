package com.example.picpay.picpay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.picpay.picpay.domain.transactions.Transaction;
import com.example.picpay.picpay.domain.user.User;
import com.example.picpay.picpay.dto.request.transaction.TransactionDto;
import com.example.picpay.picpay.repositories.TransactionRepository;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private UserService userService;

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private RestTemplate restTemplate;

  public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
    User sender = this.userService.findUserById(transactionDto.senderId());
    User receiver = this.userService.findUserById(transactionDto.receiverId());

    userService.validateTransaction(sender, transactionDto.amount());

    if (!authorizeTransaction(sender, transactionDto.amount())) {
      throw new Exception("Transaction not authorized");
    }

    Transaction transaction = new Transaction();
    transaction.setSender(sender);
    transaction.setReceiver(receiver);
    transaction.setAmount(transactionDto.amount());
    transaction.setTimestamp(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(transactionDto.amount()));
    receiver.setBalance(receiver.getBalance().add(transactionDto.amount()));

    transactionRepository.save(transaction);
    userService.saveUser(sender);
    userService.saveUser(receiver);

    notificationService.sendNotification(sender,
        "Transaction of " + transactionDto.amount() + " to " + receiver.getEmail() + " completed successfully.");

    notificationService.sendNotification(receiver,
        "Transaction of " + transactionDto.amount() + " from " + sender.getEmail() + " received successfully.");

    return transaction;
  }

  public boolean authorizeTransaction(User sender, BigDecimal amount) {

    ResponseEntity<Map> authorizationResponse = this.restTemplate
        .getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

    if (authorizationResponse.getStatusCode() == HttpStatus.OK
        && authorizationResponse.getBody().get("status").equals("success")) {
      return true;
    }

    return false; // Placeholder for actual logic
  }


  public List<Transaction> findAllTransactions() {
    return transactionRepository.findAll();
  }
}
