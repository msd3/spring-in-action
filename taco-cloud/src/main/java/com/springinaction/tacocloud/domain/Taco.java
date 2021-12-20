package com.springinaction.tacocloud.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Taco implements Serializable {

  private static final long serialVersionUID = 988_435_341_674L;

  @Id
  private UUID id;

  @NotNull
  @Size(min = 5, message = "Name must be at least five characters long")
  private String name;

  @NotNull
  @Size(min = 1, message = "You must choose at least one ingredient")
  @ManyToMany()
  private List<Ingredient> ingredients;

  private OffsetDateTime createdAt = OffsetDateTime.now();
}
