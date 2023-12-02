package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.Order;
import net.croz.owasp.goodexample.entity.UserBuyer;
import net.croz.owasp.goodexample.service.command.CreateOrderCommand;

public interface OrderService {

    Order placeOrder(Long id, CreateOrderCommand createOrderCommand, UserBuyer userBuyer);

}
