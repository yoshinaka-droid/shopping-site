package com.example.shop;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ShopController {

 private final JsonDB db;

 public ShopController(JsonDB db){
  this.db=db;
 }

 @GetMapping("/products")
 public Map<String,String> products() throws Exception{
  return db.getAll("products");
 }

 @PostMapping("/order")
 public String order(@RequestBody Map<String,String> body) throws Exception{

  String items=body.get("items");

  String id="order"+System.currentTimeMillis();

  db.put("orders",id,items);

  return "ordered";
 }

}