package com.serasa.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.serasa.order.application.service.OrderService;
import com.serasa.order.domain.dto.OrderDTO;
import com.serasa.order.domain.dto.UserDTO;
import com.serasa.order.domain.model.Order;
import com.serasa.order.presentation.controller.OrderController;


@SpringBootTest
public class OrderControllerTests {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrders() {
        List<OrderDTO> orders = Arrays.asList(
                new OrderDTO(new Order(), new UserDTO()),
                new OrderDTO(new Order(), new UserDTO())
        );
        when(orderService.findAll()).thenReturn(orders);

        ResponseEntity<List<OrderDTO>> response = orderController.getOrders();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    public void testGetOrder() {
        OrderDTO order =  new OrderDTO(new Order(), new UserDTO());
        order.setId(1L);
        order.setItemDescription("Item 1");
        when(orderService.findById(1L)).thenReturn(order);

        ResponseEntity<OrderDTO> response = orderController.getOrder(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getItemDescription()).isEqualTo("Item 1");
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        when(orderService.save(any(Order.class))).thenReturn(order);

        ResponseEntity<Order> response = orderController.createOrder(order);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        when(orderService.updateOrder(eq(1L), any(Order.class))).thenReturn(order);

        ResponseEntity<Order> response = orderController.updateOrder(1L, order);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderService).deleteOrder(1L);

        ResponseEntity<Void> response = orderController.deleteOrder(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}