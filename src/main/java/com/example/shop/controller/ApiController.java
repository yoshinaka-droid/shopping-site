package com.example.shop.controller;

import com.example.shop.service.JsonDB;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

 @PostMapping("/order")
 public String order(@RequestBody Map<String,String> body) throws Exception{

  String id="order"+System.currentTimeMillis();

  db.put("orders",id,body.get("items"));

  return "ok";
 }

}