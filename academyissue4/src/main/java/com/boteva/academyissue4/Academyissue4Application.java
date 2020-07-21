package com.boteva.academyissue4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class Academyissue4Application
{

  public static void main(String[] args)
  {
    SpringApplication app = new SpringApplication(Academyissue4Application.class);
    app.run(args);
    System.out.println("Hello World");

  }

}
