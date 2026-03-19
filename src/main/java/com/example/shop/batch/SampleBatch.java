package com.example.shop.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleBatch {

 @Scheduled(cron = "0 0 0 * * *")
 public void run(){
  System.out.println("batch running...");
 }

}