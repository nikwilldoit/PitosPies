package com.nikolas.app.repositories;

import com.nikolas.app.models.OrderItem;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends ListCrudRepository<OrderItem, Integer> {

    List<OrderItem> findOrderItemsByOrderId(int orderId);

    @Query("SELECT order_id, pie_id, quantity FROM order_item WHERE order_id=:order_id AND quantity>0 ")
    List<OrderItem> orderItems(@Param("order_id") int orderId);

}