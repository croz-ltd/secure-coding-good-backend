package net.croz.owasp.goodexample.mapper;

import net.croz.owasp.goodexample.entity.Order;
import net.croz.owasp.goodexample.service.message.OrderMessage;
import org.springframework.stereotype.Component;

@Component
public class OrderOrderMessageCreateMapper implements CreateMapper<Order, OrderMessage> {

    @Override
    public OrderMessage map(Order order) {
        final OrderMessage orderMessage = new OrderMessage();

        orderMessage.setProductId(order.getProduct().getId());
        orderMessage.setProductName(order.getProduct().getName());
        orderMessage.setBuyerId(order.getBuyer().getId());
        orderMessage.setQuantity(order.getQuantity());

        return orderMessage;
    }

}
