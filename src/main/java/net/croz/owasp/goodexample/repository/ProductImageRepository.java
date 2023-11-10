package net.croz.owasp.goodexample.repository;

import net.croz.owasp.goodexample.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Optional<ProductImage> findFirstByFilename(String filename);

}
