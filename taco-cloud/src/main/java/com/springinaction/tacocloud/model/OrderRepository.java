package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}
