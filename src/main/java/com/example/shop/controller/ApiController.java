package com.example.shop.controller;

import com.example.shop.service.JsonDB;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

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

 
 @PostMapping("/login")
 public String login(@RequestBody Map<String,String> body,
    HttpSession session){
        
        String user=body.get("user");
        String pass=body.get("pass");
        
        if("admin".equals(user) && "password".equals(pass)){
            session.setAttribute("login",true);
            return "ok";
        }
        
        return "ng";
    }
    
    @GetMapping("/products")
    public Map<String,Object> products() throws Exception{
        return db.getAllObject("products");
    }
    
private void checkLogin(HttpSession session){

    Boolean login=(Boolean)session.getAttribute("login");

    if(login==null || !login){
    throw new RuntimeException("unauthorized");
    }

}
@PostMapping("/admin/product")
public String addProduct(@RequestBody Map<String,Object> body,
                         HttpSession session) throws Exception{

 checkLogin(session);
        
 String id="p"+System.currentTimeMillis();

 db.putObject("products",id,body);

 return "ok";
}

@DeleteMapping("/admin/product/{id}")
public String deleteProduct(@PathVariable String id,
                            HttpSession session) throws Exception{

 checkLogin(session);

 Map<String,Object> data=db.getAllObject("products");

 data.remove(id);

 db.write("products",data);

 return "ok";
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