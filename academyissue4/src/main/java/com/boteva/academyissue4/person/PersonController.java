package com.boteva.academyissue4.person;

import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController
{
  private final PersonService personService;

  public PersonController(PersonService personService)
  {
    this.personService = personService;
  }


  // -- begin CRUD operations

  // HTTP POST /person
  // CREATE
  @PostMapping
  public ResponseEntity<Void> createPerson(@RequestBody PersonModel model)
  {
    long id = personService.insertPerson(model);

    // link to get method - getPerson(id)
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(id)
        .toUri();
    return ResponseEntity.created(location).build();
  }

  // HTTP GET /person/{id}
  // READ
  @GetMapping("/{id}")
  public ResponseEntity<PersonModel> getPerson(@PathVariable Long id)
  {
    PersonModel person = personService.findPersonById(id);
    if (null == person) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(person);
  }

  // HTTP PUT /person/{id}
  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<PersonModel> updatePersonById(@PathVariable Long id, @RequestBody PersonModel model)
  {
    PersonModel person = personService.findPersonById(id);
    if (null == person) {
      return ResponseEntity.notFound().build();
    }
    person.setGivenName(model.getGivenName());
    person.setSurname(model.getSurname());
    person.setEGN(model.getEGN());
    personService.updatePersonById(person);
    return ResponseEntity.ok(model);
  }

  // HTTP DELETE /person/{id}
  // DELETE
  @DeleteMapping("/{id}")
  public void deletePersonById(@PathVariable Long id)
  {
    personService.deletePersonById(id);
  }

  //Напишете Rest услуга, която позволява:  комплексно, по много колони, терсене на рейтинги; комплексно сортиране; търсене по комбинирания рeйтинг (grade_a|  grade_b | grade_c   )
  @GetMapping
  public List<PersonGrade> findPeople(@RequestParam String grade_a, @RequestParam String grade_b, @RequestParam String grade_c)
  {
    return personService.findPersonByGrades(grade_a, grade_b, grade_c);
  }
}




