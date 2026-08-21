package com.nikolas.app.repositories;

import com.nikolas.app.models.Order;
import com.nikolas.app.models.Pie;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
