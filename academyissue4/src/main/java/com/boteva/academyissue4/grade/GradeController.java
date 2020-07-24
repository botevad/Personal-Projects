package com.boteva.academyissue4.grade;

import com.boteva.academyissue4.person.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person/{personId}/grades")
public class GradeController
{
  @Autowired
  private GradeDao gradeDao;

  @PostMapping("/add")
  public ResponseEntity<GradeModel> createGrade(@RequestBody GradeModel grade){
    gradeDao.createGrade(grade);
    return ResponseEntity.ok(grade);
  }
  @GetMapping
  public ResponseEntity<GradeModel> getGrade(@PathVariable Long personId)
  {
    GradeModel grade = gradeDao.readGrade(personId);
    return null == grade
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(grade);
  }

  @PutMapping ("/{id}")
  public GradeModel updateGrade(@PathVariable Long personId, @RequestBody GradeModel grade)
  {
    gradeDao.updateGrade(personId, grade);
    grade.setPersonId(personId);
    grade.setGradeA(grade.getGradeA());
    grade.setGradeB(grade.getGradeB());
    grade.setGradeC(grade.getGradeC());
    return grade;
  }

  @DeleteMapping("/{id}")
  public void deleteGradesById(@PathVariable Long id)
  {
    gradeDao.deleteGradesById(id);
  }


}
