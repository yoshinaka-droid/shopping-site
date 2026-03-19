package com.example.shop.controller;

import com.example.shop.service.JsonDB;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

 private final JsonDB db;

 public ApiController(JsonDB db){
  this.db=db;
 }

 @GetMapping("/products")
 public Map<String,String> products() throws Exception{
  return db.getAll("products");
 }

 @GetMapping("/orders")
 public Map<String,Object> orders() throws Exception{
  return db.getAllObject("orders");
 }

    @PostMapping("/order")
    public String order(@RequestBody Map<String,Object> body) throws Exception{

    String id="order"+System.currentTimeMillis();

    Map<String,Object> orderData=new HashMap<>();

    orderData.put("items", body.get("items"));
    orderData.put("time", java.time.LocalDateTime.now().toString());

    db.putObject("orders",id,orderData);

    return "ok";
    }

}