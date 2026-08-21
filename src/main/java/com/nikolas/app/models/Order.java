package com.nikolas.app.models;

import com.nikolas.app.controllers.forms.FormDataOrder;
import com.nikolas.app.controllers.forms.custom_validators.AtLeastOneItemInOrderConstraint;
import com.nikolas.app.controllers.forms.custom_validators.OrderItemValuesConstraint;
import com.nikolas.app.controllers.forms.custom_validators.OrderTimestampConstraint;
import com.nikolas.app.controllers.forms.custom_validators.TelephoneConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Table("order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private Integer id;

    private String fullname;

    private String address;

    private String email;

    private String tel;

    private String comments;

    private boolean offer;

    private String payment;

    private LocalDateTime stamp;

    private Integer areaId;

    @MappedCollection(idColumn = "order_id")
    private Set<OrderItem> orderItem;

    public Order(FormDataOrder formDataOrder, Map<Integer, Integer> order) {
        this.id = null;
        this.fullname = formDataOrder.getFullname();
        this.address = formDataOrder.getAddress();
        this.email = formDataOrder.getEmail();
        this.tel = formDataOrder.getTel();
        this.comments = formDataOrder.getComments();

        this.offer = formDataOrder.isOffer();
        this.payment = formDataOrder.getPayment();
        this.stamp = formDataOrder.getStamp();

        this.areaId = formDataOrder.getAreaId();

        this.orderItem = new HashSet<>();
        for (var pieId: order.keySet()) {
            orderItem.add(new OrderItem(
                    pieId,
                    null,
                    order.get(pieId)
            ));
        }
    }

}
