package com.springinaction.tacocloud.model;

import com.springinaction.tacocloud.domain.TacoOrder;
import com.springinaction.tacocloud.domain.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

  List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
