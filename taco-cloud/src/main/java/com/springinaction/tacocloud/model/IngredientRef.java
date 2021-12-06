package com.springinaction.tacocloud.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@RequiredArgsConstructor
@Value
public class IngredientRef {

	private final String ingredient;

}
