package net.croz.owasp.goodexample.mapper;

import net.croz.owasp.goodexample.controller.response.OrderResponse;
import net.croz.owasp.goodexample.controller.response.ProductResponse;
import net.croz.owasp.goodexample.controller.response.UserResponse;
import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.entity.Order;
import net.croz.owasp.goodexample.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderOrderResponseMapper implements CreateMapper<Order, OrderResponse> {

    private final CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper;

    private final CreateMapper<Product, ProductResponse> productProductResponseCreateMapper;

    @Autowired
    public OrderOrderResponseMapper(CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper,
        CreateMapper<Product, ProductResponse> productProductResponseCreateMapper) {
        this.authUserUserResponseCreateMapper = authUserUserResponseCreateMapper;
        this.productProductResponseCreateMapper = productProductResponseCreateMapper;
    }

    @Override
    public OrderResponse map(Order order) {
        final OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setQuantity(order.getQuantity());
        orderResponse.setProduct(productProductResponseCreateMapper.map(order.getProduct()));
        orderResponse.setBuyer(authUserUserResponseCreateMapper.map(order.getBuyer()));

        return orderResponse;
    }

}
