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
    MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("personId", grade.getPersonId())
        .addValue("grade_a", grade.getGradeA())
        .addValue("grade_b", grade.getGradeB())
        .addValue("grade_c", grade.getGradeC());
    namedTemplate.update(insertQuery, params);

//          .addValue("grade_a", grade.getGradeA() == null ? null : grade.getGradeA().name())
//      .addValue("grade_b", grade.getGradeB() == null ? null : grade.getGradeB().name())
//      .addValue("grade_c", grade.getGradeC() == null ? null : grade.getGradeC().name());
  }

//  public void createGrade(Long personId, String grade_a, String grade_b, String grade_c ) {
//    final String insertQuery = "INSERT INTO grade (personId, grade_a, grade_b, grade_c) " +
//        "VALUES (:personId, :grade_a, :grade_b, :grade_c)";
//  }

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
            model.setGradeA((rs.getString("grade_a")));
            model.setGradeB((rs.getString("grade_b")));
            model.setGradeC((rs.getString("grade_c")));
            return model;
          }
      );
    }
    catch (EmptyResultDataAccessException ex) {
      return null;
    }
  }

//  //private static Grades of(String s)
//  {
//    return null == s ? null : Grades.valueOf(s);
//  }

  public void updateGrade(Long personId, GradeModel grade)
  {
    final String updateQuery = "UPDATE grade " +
        "SET grade_a = :grade_a, grade_b = :grade_b, grade_c = :grade_c " +
        "WHERE personId = :personId";
    namedTemplate.update(updateQuery, new MapSqlParameterSource()
        .addValue("personId", personId)
        .addValue("grade_a", grade.getGradeA())
        .addValue("grade_b", grade.getGradeB())
        .addValue("grade_c", grade.getGradeC()));

//     .addValue("grade_a", grade.getGradeA() == null ? null : grade.getGradeA())
//      .addValue("grade_b", grade.getGradeB() == null ? null : grade.getGradeB())
//      .addValue("grade_c", grade.getGradeC() == null ? null : grade.getGradeC()));
  }

  public void deleteGradesById(Long personId)
  {
    final String deleteQuery = "DELETE FROM grade WHERE personId = :personId";
    namedTemplate.update(deleteQuery, new MapSqlParameterSource("personId", personId));
  }
}
