package com.boteva.academyissue4.person;

import com.boteva.academyissue4.BaseApplicationTest;
import com.boteva.academyissue4.grade.GradeModel;
import com.boteva.academyissue4.grade.Grades;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testng.Assert.*;

public class ControllersTest extends BaseApplicationTest
{

  @Autowired
  PersonDao personDao;

  @Autowired
  NamedParameterJdbcOperations namedTemplate;

  @BeforeMethod
  public void setUp()
  {
  }

  @AfterMethod
  public void tearDown()
  {
  }

//  @Test
//  public void testFilter() throws Exception {
//
//      personDao.insertPerson(new PersonModel(null, "Ivan", "Ivanov", "1234567890"));
//  }

  @Test
  public void testPersonCRUDOperations() throws Exception
  {
    PersonModel person = new PersonModel();
    person.setGivenName("Ivan");
    person.setSurname("Ivanov");
    person.setEGN((long)(787878778));

     //create
    String uri = mvc.perform(post("/person")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(person)))
        .andExpect(status().isCreated())
        .andExpect(header().exists(HttpHeaders.LOCATION))
        .andReturn().getResponse().getHeader(HttpHeaders.LOCATION);

     //read
    mvc.perform(get(uri))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Ivan"))
        .andExpect(jsonPath("$.surname").value("Ivanov"))
        .andExpect(jsonPath("$.egn").value("7878787788"));

     //read grades - automatically created with null values
    mvc.perform(get(uri + "/grades"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.grade_a").isEmpty())
        .andExpect(jsonPath("$.grade_b").isEmpty())
        .andExpect(jsonPath("$.grade_c").isEmpty());

     //update grade
    GradeModel gradeModel = new GradeModel(null, "G", "D", "C");
    mvc.perform(put(uri + "/grades")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(gradeModel)))
        .andExpect(status().isOk());
    mvc.perform(get(uri + "/grades"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.grade_a").value("G"))
        .andExpect(jsonPath("$.grade_b").value("D"))
        .andExpect(jsonPath("$.grade_c").value("A"));

    // find - successful
    mvc.perform(get("/person")
        .queryParam("grade_a", "G")
        .queryParam("grade_b", "D")
        .queryParam("grade_c", "A"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1));

    // find - no match
    mvc.perform(get("/person")
        .queryParam("grade_a", "A")
        .queryParam("grade_b", "D")
        .queryParam("grade_c", "A"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(0));


    // update
    person.setGivenName("Petar");
    person.setSurname("Petrov");
    person.setEGN((long)1111111111);
    mvc.perform(put(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(person)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Petar"))
        .andExpect(jsonPath("$.surname").value("Petrov"))
        .andExpect(jsonPath("$.egn").value("1111111111"));


    // delete
    mvc.perform(delete(uri))
        .andExpect(status().isOk());
    mvc.perform(get(uri))
        .andExpect(status().isNotFound());
    mvc.perform(get(uri + "/grades"))
        .andExpect(status().isNotFound());

  }


}