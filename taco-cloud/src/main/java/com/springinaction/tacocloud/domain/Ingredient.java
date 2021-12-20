package com.springinaction.tacocloud.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
//@RequiredArgsConstructor
public class Ingredient {

	@Id
	private String id;
	private String name;
	private Type type;

	public void setId(String id) {
		this.id = id;
	}

	@javax.persistence.Id
	public String getId() {
		return id;
	}

	public enum Type {
		WRAP,
		PROTEIN,
		VEGGIES,
		CHEESE,
		SAUCE;
	}
}

