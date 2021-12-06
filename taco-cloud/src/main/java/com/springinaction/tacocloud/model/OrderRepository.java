package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.TacoOrder;

public interface OrderRepository {

	TacoOrder save(TacoOrder order);

}
