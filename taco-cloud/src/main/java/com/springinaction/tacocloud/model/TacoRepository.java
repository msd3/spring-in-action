package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {}
