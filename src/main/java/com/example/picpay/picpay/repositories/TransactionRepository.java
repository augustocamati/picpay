package com.example.picpay.picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.picpay.picpay.domain.transactions.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

   

}
