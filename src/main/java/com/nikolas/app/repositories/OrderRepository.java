package com.nikolas.app.repositories;

import com.nikolas.app.models.Order;
import com.nikolas.app.models.OrderItem;
import com.nikolas.app.models.Pie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("SELECT * FROM `order` o WHERE o.user_id = :user_id ORDER BY o.stamp DESC LIMIT 5 ")
    List<Order> findTopFiveUserOrderIds(@Param("user_id") int user_id);

    @Query("SELECT * FROM order_item WHERE order_id=:order_id AND quantity>0 ")
    List<OrderItem> orderItems(@Param("order_id") int order_id);

    Order findOrderById(int id);
}
