package com.boteva.academyissue4.person;

public class PersonGrade
{
  private String personId;
  private String givenName;
  private String surname;
  private String grade_a;
  private String grade_b;
  private String grade_c;

  public PersonGrade()
  {
  }

  public PersonGrade(String personId, String givenName, String surname, String grade_a, String grade_b, String grade_c)
  {
    this.personId = personId;
    this.givenName = givenName;
    this.surname = surname;
    this.grade_a = grade_a;
    this.grade_b = grade_b;
    this.grade_c = grade_c;
  }

  public String getPersonId()
  {
    return personId;
  }

  public void setPersonId(String personId)
  {
    this.personId = personId;
  }

  public String getGivenName()
  {
    return givenName;
  }

  public void setGivenName(String givenName)
  {
    this.givenName = givenName;
  }

  public String getSurname()
  {
    return surname;
  }

  public void setSurname(String surname)
  {
    this.surname = surname;
  }

  public String getGrade_a()
  {
    return grade_a;
  }

  public void setGrade_a(String grade_a)
  {
    this.grade_a = grade_a;
  }

  public String getGrade_b()
  {
    return grade_b;
  }

  public void setGrade_b(String grade_b)
  {
    this.grade_b = grade_b;
  }

  public String getGrade_c()
  {
    return grade_c;
  }

  public void setGrade_c(String grade_c)
  {
    this.grade_c = grade_c;
  }
}
