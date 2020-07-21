package com.boteva.academyissue4.person;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonModel
{
  @JsonProperty("id") // tova ni kazva kak se konvertirat dannite v json format
  private Long   personId;
  @JsonProperty("name")
  private String givenName;
  @JsonProperty("surname")
  private String surname;
  @JsonProperty("egn")
  private String EGN;

  public PersonModel(Long personId, String givenName, String surname, String EGN)
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

  public String getEGN()
  {
    return EGN;
  }

  public void setEGN(String EGN)
  {
    this.EGN = EGN;
  }
}
