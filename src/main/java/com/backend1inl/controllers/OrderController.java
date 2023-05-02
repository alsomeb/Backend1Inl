package com.backend1inl.controllers;



import com.backend1inl.domain.OrderDTO;
import com.backend1inl.domain.OrderItemDTO;
import com.backend1inl.services.OrderService;
import com.backend1inl.utils.DeleteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    // tex  .../orders/buy?itemId=10004&orderId=10001&quantity=3
    @PostMapping("buy")
    public ResponseEntity<OrderItemDTO> addItemToOrder(@RequestParam Long itemId, @RequestParam Long orderId, @RequestParam int quantity) {
        OrderItemDTO savedOrderItemDTO = orderService.addItemToOrder(orderId, itemId, quantity);

        log.info("Added item {} to order {}", itemId, orderId);
        return new ResponseEntity<>(savedOrderItemDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> allOrders = orderService.getAllOrders();
        log.info("Showing all, {}x orders in db", allOrders.size());

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    // Hämta Alla Items för ett visst order Id
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getItemsByOrderId(@PathVariable Long id) {
        OrderDTO order = orderService.getItemsByOrderId(id);

        log.info("Showing all items on order: {}", id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponse> deleteOrderById(@PathVariable Long id) {
        var response = orderService.deleteOrderById(id);
        log.info("Delete response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Ta bort Order By Id
    // Ta bort Item på spec Order
}
