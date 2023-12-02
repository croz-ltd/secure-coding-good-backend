package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.Order;
import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.UserBuyer;
import net.croz.owasp.goodexample.repository.OrderRepository;
import net.croz.owasp.goodexample.service.OrderService;
import net.croz.owasp.goodexample.service.ProductService;
import net.croz.owasp.goodexample.service.command.CreateOrderCommand;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Override
    public Order placeOrder(Long id, CreateOrderCommand createOrderCommand, UserBuyer userBuyer) {
        final Product product = productService.findById(id);

        final Order order = new Order();

        order.setCreationDate(LocalDateTime.now());
        order.setProduct(product);
        order.setBuyer(userBuyer);
        order.setQuantity(createOrderCommand.getQuantity());

        return orderRepository.save(order);
    }

}
