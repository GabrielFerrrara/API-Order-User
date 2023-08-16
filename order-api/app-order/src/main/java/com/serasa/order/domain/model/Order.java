package com.serasa.order.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name= "order_table" )
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @Column(name = "total_value")
    private BigDecimal totalValue;

	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Order() {}

    public Order(Long id, Long userId ,String itemDescription) {
    }

}