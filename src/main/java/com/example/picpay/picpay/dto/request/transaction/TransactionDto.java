package com.example.picpay.picpay.dto.request.transaction;

import java.math.BigDecimal;

public record TransactionDto(
    Long senderId,
    Long receiverId,
    BigDecimal amount
) {
  
}
