package com.boteva.academyissue4.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonModel
{
  @JsonIgnore // tova ni kazva kak se konvertirat dannite v json format
  private Long   personId;
  @JsonProperty("givenName")
  private String givenName;
  @JsonProperty("surname")
  private String surname;
 @JsonProperty("EGN")
  private Long EGN;

  public PersonModel(Long personId, String givenName, String surname, Long EGN)
  {
    this.personId = personId;
    this.givenName = givenName;
    this.surname = surname;
    this.EGN = EGN;
  }

  public PersonModel()
  {
  }

  public Long getPersonId()
  {
    return personId;
  }

  public void setPersonId(Long personId)
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

  public Long getEGN()
  {
    return EGN;
  }

  public void setEGN(Long EGN)
  {
    this.EGN = EGN;
  }
}
