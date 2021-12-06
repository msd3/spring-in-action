package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.TacoOrder;

public class JdbcOrderRepository implements OrderRepository{

	@Override
	public TacoOrder save(TacoOrder order) {
		return null;
	}
}
