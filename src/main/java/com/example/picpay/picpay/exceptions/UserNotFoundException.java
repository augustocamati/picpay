package com.example.picpay.picpay.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends PicPayException {

  private Long userId;

  public UserNotFoundException(Long userId) {
    this.userId = userId;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("User not found.");
    pb.setDetail("User with ID " + userId + " does not exist in the system.");

    return pb;
  }
}