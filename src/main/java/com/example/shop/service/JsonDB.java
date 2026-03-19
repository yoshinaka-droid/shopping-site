package com.example.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsonDB {

 private final ObjectMapper mapper = new ObjectMapper();

 private File getFile(String name){
  return new File("/data/"+name+".json");
 }

 @PostConstruct
 public void init() throws Exception{

  initFile("products", Map.of(
   "p1","Apple",
   "p2","Orange",
   "p3","Banana"
  ));

  initFile("orders", new HashMap<>());

 }

 private void initFile(String name, Map<String,String> initData) throws Exception{

  File file=getFile(name);

  file.getParentFile().mkdirs();

  if(!file.exists() || file.length()==0){
   mapper.writerWithDefaultPrettyPrinter()
    .writeValue(file,initData);
  }

 }

 public synchronized Map<String,String> getAll(String name) throws Exception{
  return mapper.readValue(getFile(name),Map.class);
 }

 public synchronized void put(String name,String key,String value) throws Exception{

  Map<String,String> data=getAll(name);

  data.put(key,value);

  mapper.writerWithDefaultPrettyPrinter()
   .writeValue(getFile(name),data);

 }

}