package com.example.picpay.picpay.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.picpay.picpay.domain.user.User;
import com.example.picpay.picpay.dto.request.NotificationDto;
@Service
public class NotificationService {
  @Autowired
  private RestTemplate restTemplate;

  public void sendNotification(User user, String message) {
   String email = user.getEmail();
   NotificationDto notification = new NotificationDto(email, message);
   ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify",  notification, String.class);

   if(!response.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException("Failed to send notification");
    
    }
    System.out.println("Notification sent: " + message);
}
}
