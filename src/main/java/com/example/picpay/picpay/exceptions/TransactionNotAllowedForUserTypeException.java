package com.example.picpay.picpay.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransactionNotAllowedForUserTypeException extends PicPayException {

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("This wallet type is not allowed to transfer.");

    return pb;
  }
}