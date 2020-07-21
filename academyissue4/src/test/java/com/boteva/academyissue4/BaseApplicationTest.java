package com.boteva.academyissue4;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

// винаги стартираме с профил test - това избира тестовата база данни
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApplicationTest extends AbstractTestNGSpringContextTests
{
  protected final ObjectMapper          objectMapper = new ObjectMapper();
  protected       MockMvc               mvc;
  @Autowired
  private         WebApplicationContext webApplicationContext;

  public static String asJsonString(final Object obj)
  {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeClass
  public void setup()
  {
    mvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .build();
  }
}
