package com.mzk.bzj.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"spring-config.xml"})
public class Starter
{
  public static void main(String[] args)
  {
    SpringApplication.run(Starter.class, args);
  }
}