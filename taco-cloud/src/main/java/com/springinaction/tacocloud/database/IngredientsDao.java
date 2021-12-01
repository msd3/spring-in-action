package com.springinaction.tacocloud.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class IngredientsDao {

    private final JdbcTemplate jdbcTemplate;
    //NamedParameterJdbcTemplate

    private final SimpleJdbcInsert ingredientInserter;

    public IngredientsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.ingredientInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("ingredients"); 
    }

    public Stream<Ingredient> findAll() {
        return jdbcTemplate.queryForStream("select * from ingredients",
                (rs, rowNum) -> new Ingredient(
                        rs.getObject(1, UUID.class),
                        rs.getString(2))
        );
    }

    public UUID create(String ingredientName) {
        var id = UUID.randomUUID();
        ingredientInserter.execute(Map.of(
                "id", id,
                "name", ingredientName
        ));
        return id;
    }
}
