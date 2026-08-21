package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer pieId;
    private Integer orderId;
    private Integer quantity;
}
