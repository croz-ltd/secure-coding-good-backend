package net.croz.owasp.goodexample.controller;

import net.croz.owasp.goodexample.controller.response.OrderResponse;
import net.croz.owasp.goodexample.entity.Order;
import net.croz.owasp.goodexample.mapper.CreateMapper;
import net.croz.owasp.goodexample.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    private final CreateMapper<Order, OrderResponse> orderOrderResponseCreateMapper;

    @Autowired
    public OrderController(OrderService orderService,
        CreateMapper<Order, OrderResponse> orderOrderResponseCreateMapper) {
        this.orderService = orderService;
        this.orderOrderResponseCreateMapper = orderOrderResponseCreateMapper;
    }

    // OWASP[84]
    // OWASP[85]
    // OWASP[87]
    // OWASP[88]
    @GetMapping("/")
    @PreAuthorize("@orderSecurityServiceImpl.canFindAll(authentication.principal)")
    public List<OrderResponse> findAll() {
        return orderOrderResponseCreateMapper.mapToList(orderService.findAll());
    }

}
