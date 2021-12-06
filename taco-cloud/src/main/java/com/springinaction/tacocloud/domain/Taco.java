package com.springinaction.tacocloud.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco implements Serializable {

  private static final long serialVersionUID = 988_435_341_674L;

  @NotNull
  @Size(min = 5, message = "Name must be at least five characters long")
  private String name;

  @NotNull
  @Size(min = 1, message = "You must choose at least one ingredient")
  private List<Ingredient> ingredients;

  private OffsetDateTime createdAt = OffsetDateTime.now();
}
