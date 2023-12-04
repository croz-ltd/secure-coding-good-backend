package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.Order;
import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.UserBuyer;
import net.croz.owasp.goodexample.exception.TransactionLimitException;
import net.croz.owasp.goodexample.mapper.CreateMapper;
import net.croz.owasp.goodexample.repository.OrderRepository;
import net.croz.owasp.goodexample.service.MessagingService;
import net.croz.owasp.goodexample.service.OrderService;
import net.croz.owasp.goodexample.service.ProductService;
import net.croz.owasp.goodexample.service.command.CreateOrderCommand;
import net.croz.owasp.goodexample.service.message.OrderMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    final ProductService productService;

    private final MessagingService messagingService;

    private final CreateMapper<Order, OrderMessage> orderOrderMessageCreateMapper;

    private final static String PRODUCT_ORDER_MESSAGE_DESTINATION = "/product/%d";

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService,
        MessagingService messagingService, CreateMapper<Order, OrderMessage> orderOrderMessageCreateMapper) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.messagingService = messagingService;
        this.orderOrderMessageCreateMapper = orderOrderMessageCreateMapper;
    }

    @Override
    public Order placeOrder(Long id, CreateOrderCommand createOrderCommand, UserBuyer userBuyer) {
        final Product product = productService.findById(id);

        if (!canPlaceOrder(product, createOrderCommand, userBuyer)) {
            throw new TransactionLimitException();
        }

        final Order order = new Order();

        order.setCreationDate(LocalDateTime.now());
        order.setProduct(product);
        order.setBuyer(userBuyer);
        order.setQuantity(createOrderCommand.getQuantity());

        final Order savedOrder = orderRepository.save(order);

        final String topic = String.format(PRODUCT_ORDER_MESSAGE_DESTINATION, product.getSeller().getId());

        messagingService.sendMessage(topic, orderOrderMessageCreateMapper.map(savedOrder));

        return savedOrder;
    }

    // TODO: da li je ovo zbilja najbolja implementacija - bbes
    public boolean canPlaceOrder(Product product, CreateOrderCommand createOrderCommand, UserBuyer userBuyer) {
        final List<Order> orders = orderRepository.findAllByBuyerAndCreationDateBetween(
            userBuyer,
            LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
            LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
        );

        final BigDecimal ordersTodaySum = orders.stream()
            .map(t -> t.getProduct().getPrice().multiply(new BigDecimal(t.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal orderSum = product.getPrice().multiply(new BigDecimal(createOrderCommand.getQuantity()));

        final BigDecimal total = ordersTodaySum.add(orderSum);

        return total.compareTo(new BigDecimal(1000)) < 0;
    }

}
