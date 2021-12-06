package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.Ingredient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Ingredient> findAll() {
    return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
  }

  @Override
  public Optional<Ingredient> findById(String id) {
    jdbcTemplate.query(
        "select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
    return Optional.empty();
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbcTemplate.update(
        "insert into Ingredient (id, name, type) values (?, ?, ?)",
        ingredient.getId(),
        ingredient.getName(),
        ingredient.getType());
    return ingredient;
  }

  private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
    return new Ingredient(
        row.getString("id"), row.getString("name"), Ingredient.Type.valueOf(row.getString("type")));
  }
}
