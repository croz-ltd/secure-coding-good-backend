package net.croz.owasp.goodexample.repository;

import net.croz.owasp.goodexample.entity.UserSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSellerRepository extends JpaRepository<UserSeller, Long> {

}
