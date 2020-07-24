package com.boteva.academyissue4.grade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeModel
{
  @JsonProperty("personId")
  private Long   personId;
  @JsonProperty("grade_a")
  private String grade_a;
  @JsonProperty("grade_b")
  private String grade_b;
  @JsonProperty("grade_c")
  private String grade_c;

  public GradeModel()
  {
    // default constructor
  }

  public GradeModel(Long personId, String grade_a, String grade_b, String grade_c)
  {
    this.personId = personId;
    this.grade_a = grade_a;
    this.grade_b = grade_b;
    this.grade_c = grade_c;
  }

  public Long getPersonId()
  {
    return personId;
  }

  public void setPersonId(Long personId)
  {
    this.personId = personId;
  }

  public String getGradeA()
  {
    return grade_a;
  }

  public void setGradeA(String grade_a)
  {
    this.grade_a = grade_a;
  }

  public String getGradeB()
  {
    return grade_b;
  }

  public void setGradeB(String grade_b)
  {
    this.grade_b = grade_b;
  }

  public String getGradeC()
  {
    return grade_c;
  }

  public void setGradeC(String grade_c)
  {
    this.grade_c = grade_c;
  }
}
