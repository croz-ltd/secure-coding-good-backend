package net.croz.owasp.goodexample.repository;

import net.croz.owasp.goodexample.entity.UserBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBuyerRepository extends JpaRepository<UserBuyer, Long> {

}
