package com.nikolas.app.controllers.dtos;

import com.nikolas.app.models.Pie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class PreviousOrder {
    @Data
    @AllArgsConstructor
    private class OrderItem {
        String pie;
        int quantity;

        @Override
        public String toString() {
            return  pie + '(' +
                    quantity +
                    ')';
        }
    }

    LocalDateTime stamp;
    List<OrderItem> items;

    Integer orderId;


    private String getPieName(int id, List<Pie> pies) {
        for (Pie pie: pies)
            if (id == pie.getId())
                return pie.getName();
        return null;
    }

    public PreviousOrder(LocalDateTime stamp, Integer orderId, Set<com.nikolas.app.models.OrderItem> orderItemSet, List<Pie> pies) {
        this.stamp = stamp;
        this.orderId = orderId;

        List<com.nikolas.app.models.OrderItem> listOfItemsInOrder = orderItemSet.stream().toList();
        items = new ArrayList<>();
        for (var setItem: listOfItemsInOrder) {
            if (setItem.getQuantity()>0)
                items.add(new OrderItem(
                        getPieName(setItem.getPieId(), pies),
                        setItem.getQuantity()));
        }
    }

    @Override
    public String toString() {
        return  stamp.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy, h:mm a")) + ": " +
                String.join(", ", Arrays.stream(items.toArray())
                        .map(Object::toString)
                        .toArray(String[]::new));
    }
}