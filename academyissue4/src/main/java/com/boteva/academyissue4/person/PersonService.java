package com.boteva.academyissue4.person;

import com.boteva.academyissue4.grade.GradeDao;
import com.boteva.academyissue4.grade.GradeModel;
import com.boteva.academyissue4.grade.Grades;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService
{
  private final PersonDao personDao;
  private final GradeDao  gradeDao;

  public PersonService(PersonDao personDao, GradeDao gradeDao)
  {
    this.personDao = personDao;
    this.gradeDao = gradeDao;
  }

  public void insertPerson(PersonModel person)
  {
    personDao.insertPerson(person);
    //long personId = personDao.insertPerson(person);
//    GradeModel grade = new GradeModel(personId, null, null, null);
//    gradeDao.createGrade(grade);
//    return personId;
  }

  public void deletePersonById(Long id)
  {
    gradeDao.deleteGradesById(id);
    personDao.deletePersonById(id);
  }

  public void updatePersonById(PersonModel pm)
  {
    personDao.updatePersonById(pm);

  }

  public PersonModel findPersonById(Long id)
  {
    return personDao.findPersonById(id);
  }

  public List<PersonGrade> findPersonByGrades(String grade_a, String grade_b, String grade_c)
  {
    return personDao.findPersonByGrades(grade_a, grade_b, grade_c);
  }
  public List<PersonModel> searchByGivenName(String givenName){
    return personDao.searchByGivenName(givenName);

  }
}
