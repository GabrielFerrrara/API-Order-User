package com.serasa.order.domain.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serasa.order.domain.model.Order;


@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    Optional<Order> findById(Long id);
    Order save(Order user);
    void delete(Order user);
}