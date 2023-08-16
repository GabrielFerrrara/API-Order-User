package com.serasa.order.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.serasa.order.domain.dto.OrderDTO;
import com.serasa.order.domain.dto.UserDTO;
import com.serasa.order.domain.exception.OrderInvalidException;
import com.serasa.order.domain.exception.OrderNotFoundException;
import com.serasa.order.domain.exception.UserInvalidException;
import com.serasa.order.domain.model.Order;
import com.serasa.order.domain.repository.JpaOrderRepository;


@Service
public class OrderService {
    
    @Autowired
    private JpaOrderRepository orderRepository;

    @Value("${userAppUrl}")
    private String userAppUrl;


    public List<OrderDTO> findAll() {
        List<Order> listOrder = orderRepository.findAll();
        List<OrderDTO> listaDTO = convertToListDTO(listOrder);
        return listaDTO;
    }

    // @Cacheable(value="orderCache"key="#id")
    public OrderDTO findById(Long id) {
        Optional<Order> userOptional = orderRepository.findById(id);
        if (userOptional.isPresent()) {
            return new OrderDTO(userOptional.get(), getUser(userOptional.get().getUserId()));
        }
        throw new OrderNotFoundException("Order not found with id: " + id);
    }
    
    public Order save(Order order) {
        try {
            getUser(order.getUserId());
            return orderRepository.save(order);
        } catch (HttpClientErrorException ex){
            throw new UserInvalidException("UserId not found in UserAPI");
        } catch (Exception e) {
            throw new OrderInvalidException("Order attribute invalid, expected: \n userId, \n itemDescription, \nitemQuantity, \nitemPrice, \ntotalValue");
        }
    }

    public Order updateOrder(Long id, Order user) throws HttpClientErrorException {
        //verify if id exist
        findById(id);
        //verify if idUser exist
        getUser(user.getUserId());
        user.setId(id);
        return save(user);
    }

    public void deleteOrder(Long id){
        findById(id);
        orderRepository.deleteById(id);
    } 

    private UserDTO getUser(Long userId) throws HttpClientErrorException {
        String url = String.format("%s/user/%s", userAppUrl, userId);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, UserDTO.class);    
  
    }

    private List<OrderDTO> convertToListDTO(List<Order> listOrder){
        List<OrderDTO> listaDTO = new ArrayList<>();
        for(Order order : listOrder){
            listaDTO.add(findById(order.getId()));
        }
        return listaDTO;
    }
    
}


