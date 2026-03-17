package com.example.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsonDB {

 private ObjectMapper mapper = new ObjectMapper();

 private synchronized Map<String,String> read(String name) throws Exception {

  File file=new File("data/"+name+".json");

  if(!file.exists()){
   return new HashMap<>();
  }

  return mapper.readValue(file,Map.class);

 }

 private synchronized void write(String name,Map<String,String> data) throws Exception{

  File file=new File("/data/"+name+".json");

  mapper.writerWithDefaultPrettyPrinter()
   .writeValue(file,data);

 }

 public Map<String,String> getAll(String db) throws Exception{
  return read(db);
 }

 public void put(String db,String key,String value) throws Exception{

  Map<String,String> data=read(db);

  data.put(key,value);

  write(db,data);

 }

}