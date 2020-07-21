package com.boteva.academyissue4.grade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeModel
{
  @JsonIgnore
  private Long   personId;
  @JsonProperty("grade_a")
  private Grades gradeA;
  @JsonProperty("grade_b")
  private Grades gradeB;
  @JsonProperty("grade_c")
  private Grades gradeC;

  public GradeModel()
  {
    // default constructor
  }

  public GradeModel(Long personId, Grades gradeA, Grades gradeB, Grades gradeC)
  {
    this.personId = personId;
    this.gradeA = gradeA;
    this.gradeB = gradeB;
    this.gradeC = gradeC;
  }

  public Long getPersonId()
  {
    return personId;
  }

  public void setPersonId(Long personId)
  {
    this.personId = personId;
  }

  public Grades getGradeA()
  {
    return gradeA;
  }

  public void setGradeA(Grades gradeA)
  {
    this.gradeA = gradeA;
  }

  public Grades getGradeB()
  {
    return gradeB;
  }

  public void setGradeB(Grades gradeB)
  {
    this.gradeB = gradeB;
  }

  public Grades getGradeC()
  {
    return gradeC;
  }

  public void setGradeC(Grades gradeC)
  {
    this.gradeC = gradeC;
  }
}
