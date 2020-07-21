package com.boteva.academyissue4.grade;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class GradeDao
{
  private final NamedParameterJdbcOperations namedTemplate;

  public GradeDao(NamedParameterJdbcOperations namedTemplate)
  {
    this.namedTemplate = namedTemplate;
  }

  public void createGrade(GradeModel grade)
  {
    final String insertQuery = "INSERT INTO grade (personId, grade_a, grade_b, grade_c) " +
        "VALUES (:personId, :grade_a, :grade_b, :grade_c)";

    namedTemplate.update(insertQuery, new MapSqlParameterSource()
        .addValue("personId", grade.getPersonId())
        .addValue("grade_a", grade.getGradeA() == null ? null : grade.getGradeA().name())
        .addValue("grade_b", grade.getGradeB() == null ? null : grade.getGradeB().name())
        .addValue("grade_c", grade.getGradeC() == null ? null : grade.getGradeC().name()));
  }

  public GradeModel readGrade(Long personId)
  {
    final String sql = "SELECT personId, grade_a, grade_b, grade_c " +
        "FROM grade " +
        "WHERE personId = :personId";
    try {
      return namedTemplate.queryForObject(
          sql, new MapSqlParameterSource("personId", personId),
          (rs, rowNum) -> {
            GradeModel model = new GradeModel();
            model.setPersonId(rs.getLong("personId"));
            model.setGradeA(of(rs.getString("grade_a")));
            model.setGradeB(of(rs.getString("grade_b")));
            model.setGradeC(of(rs.getString("grade_c")));
            return model;
          }
      );
    }
    catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

  private static Grades of(String s)
  {
    return null == s ? null : Grades.valueOf(s);
  }

  public void updateGrade(Long personId, GradeModel grade)
  {
    final String updateQuery = "UPDATE grade " +
        "SET grade_a = :grade_a, grade_b = :grade_b, grade_c = :grade_c " +
        "WHERE personId = :personId";
    namedTemplate.update(updateQuery, new MapSqlParameterSource()
        .addValue("personId", personId)
        .addValue("grade_a", grade.getGradeA() == null ? null : grade.getGradeA().name())
        .addValue("grade_b", grade.getGradeB() == null ? null : grade.getGradeB().name())
        .addValue("grade_c", grade.getGradeC() == null ? null : grade.getGradeC().name()));
  }

  public void deleteGrade(Long personId)
  {
    final String deleteQuery = "DELETE FROM grade WHERE personId = :personId";
    namedTemplate.update(deleteQuery, new MapSqlParameterSource("personId", personId));
  }
}
