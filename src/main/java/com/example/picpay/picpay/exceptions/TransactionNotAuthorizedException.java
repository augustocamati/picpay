package com.example.picpay.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransactionNotAuthorizedException extends PicPayException {

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("Transaction not authorized.");
    pb.setDetail("Authorization service did not authorize this transaction.");

    return pb;
  }
  
}
