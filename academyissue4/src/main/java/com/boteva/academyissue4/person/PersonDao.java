package com.boteva.academyissue4.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao
{
  @Autowired
  private final NamedParameterJdbcOperations namedTemplate;


  public PersonDao(NamedParameterJdbcOperations namedTemplate)
  {
    this.namedTemplate = namedTemplate;
  }


  public long insertPerson(PersonModel person)
  {
    final String insertQuery = "INSERT INTO person (personId, givenName, surname, EGN) " +
        "VALUES (sequence_personId.NEXTVAL, :givenName, :surname, :EGN)";

    MapSqlParameterSource params = new MapSqlParameterSource();
    final KeyHolder keyHolder = new GeneratedKeyHolder();

    params.addValue("givenName", person.getGivenName())
        .addValue("surname", person.getSurname())
        .addValue("EGN", person.getEGN());

    namedTemplate.update(insertQuery, params, keyHolder, new String[]{"personId"});

    return keyHolder.getKey().longValue();
  }

  public void deletePersonById(Long id)
  {
    final String deleteQuery = "DELETE FROM person WHERE personId = :personId";
    MapSqlParameterSource params = new MapSqlParameterSource("personId", id);
    namedTemplate.update(deleteQuery, params);
  }

  public void updatePersonById(PersonModel pm)
  {

    final String updateQuery = "UPDATE person " +
        "SET givenName = :givenName, surname = :surname, EGN = :EGN " +
        "WHERE personId = :personId";
    namedTemplate.update(updateQuery, new MapSqlParameterSource("personId", pm.getPersonId())
        .addValue("givenName", pm.getGivenName())
        .addValue("surname", pm.getSurname())
        .addValue("EGN", pm.getEGN()));
  }

  public PersonModel findPersonById(Long id)
  {
    final String sql =
        "SELECT personId, givenName, surname, EGN " +
            "FROM person " +
            "WHERE personId = :personId";
    try {
      return namedTemplate.queryForObject(
          sql, new MapSqlParameterSource("personId", id),
          (rs, rowNum) -> {
            PersonModel personModel = new PersonModel();
            personModel.setGivenName(rs.getString("givenName"));
            personModel.setPersonId(rs.getLong("personId"));
            personModel.setEGN(rs.getString("EGN"));
            personModel.setSurname(rs.getString("surname"));
            return personModel;
          }

      );
    }
    catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

  public List<PersonGrade> findPersonByGrades(String grade_a, String grade_b, String grade_c)
  {
    String sql = "SELECT p.personId, p.givenName, p.surname, g.grade_a, g.grade_b, g.grade_c " +
        "FROM person p " +
        "INNER JOIN grade g ON p.personId=g.personId " +
        "WHERE g.grade_a =:grade_a AND g.grade_b =:grade_b AND g.grade_c =:grade_c " +
        "ORDER BY p.givenName ASC";
    MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("grade_a", grade_a)
        .addValue("grade_b", grade_b)
        .addValue("grade_c", grade_c);
    List<PersonGrade> result = namedTemplate.query(sql, params, (resultSet, i) -> {
      PersonGrade personGrade = new PersonGrade();
      personGrade.setPersonId(resultSet.getString("personId"));
      personGrade.setGivenName(resultSet.getString("givenName"));
      personGrade.setSurname(resultSet.getString("surname"));
      personGrade.setGrade_a(resultSet.getString("grade_a"));
      personGrade.setGrade_b(resultSet.getString("grade_b"));
      personGrade.setGrade_c(resultSet.getString("grade_c"));
      return personGrade;
    });
    return result;
  }

}
