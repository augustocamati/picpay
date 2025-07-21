package com.example.picpay.picpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.picpay.picpay.domain.transactions.Transaction;
import com.example.picpay.picpay.dto.request.transaction.TransactionDto;
import com.example.picpay.picpay.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  @Autowired
  private  TransactionService transactionService;

@PostMapping
public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto createTransactionDto) throws Exception {
    Transaction transaction = transactionService.createTransaction(createTransactionDto);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
  }

  @GetMapping
  public List<Transaction> getAllTransactions() {
    List<Transaction> transactions = transactionService.findAllTransactions();
    return transactions;
  }
  
}
