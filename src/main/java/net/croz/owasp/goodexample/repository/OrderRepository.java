package net.croz.owasp.goodexample.repository;

import net.croz.owasp.goodexample.entity.Order;
import net.croz.owasp.goodexample.entity.UserBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByBuyerAndCreationDateBetween(UserBuyer buyer, LocalDateTime start, LocalDateTime end);

}
