package com.boteva.academyissue4.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person/{personId}/grades")
public class GradeController
{
  @Autowired
  private GradeDao gradeDao;


  @GetMapping
  public ResponseEntity<GradeModel> getGrade(@PathVariable Long personId)
  {
    GradeModel grade = gradeDao.readGrade(personId);
    return null == grade
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(grade);
  }

  @PutMapping
  public GradeModel updateGrade(@PathVariable Long personId, @RequestBody GradeModel grade)
  {
    gradeDao.updateGrade(personId, grade);
    grade.setPersonId(personId);
    return grade;
  }


}
