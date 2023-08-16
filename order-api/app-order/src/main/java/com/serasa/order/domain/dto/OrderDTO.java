package com.serasa.order.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// import org.springframework.data.redis.core.RedisHash;

import com.serasa.order.domain.model.Order;

import lombok.Data;

@Data
// @RedisHash("Order")
public class OrderDTO {
    private Long id;
    private UserDTO user;
    private String itemDescription;
    private Integer itemQuantity;
    private BigDecimal itemPrice;
    private BigDecimal totalValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderDTO(Order order, UserDTO user) {
        this.id = order.getId();
        this.user = user;
        this.itemDescription = order.getItemDescription();
        this.itemQuantity = order.getItemQuantity();
        this.itemPrice = order.getItemPrice();
        this.totalValue = order.getTotalValue();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }

    public OrderDTO() {
    }
}
