package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.ProductComment;
import net.croz.owasp.goodexample.entity.ProductImage;
import net.croz.owasp.goodexample.exception.EntityNotFoundException;
import net.croz.owasp.goodexample.repository.ProductCommentRepository;
import net.croz.owasp.goodexample.repository.ProductRepository;
import net.croz.owasp.goodexample.service.ProductService;
import net.croz.owasp.goodexample.service.StorageService;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;
import net.croz.owasp.goodexample.service.command.CreateProductCommentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductCommentRepository productCommentRepository;

    private final StorageService storageService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductCommentRepository productCommentRepository,
        StorageService storageService) {
        this.productRepository = productRepository;
        this.productCommentRepository = productCommentRepository;
        this.storageService = storageService;
    }

    @Transactional
    @Override
    public Product create(CreateProductCommand createProductCommand) {
        final ProductImage productImage = storageService.store(createProductCommand.getImage());

        final Product product = new Product();
        product.setName(createProductCommand.getName());
        product.setDescription(createProductCommand.getDescription());
        product.setPrice(createProductCommand.getPrice());
        product.setProductImage(productImage);

        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            Product.class, String.format("id=%s", id))
        );
    }

    @Transactional
    @Override
    public ProductComment createComment(Long id, CreateProductCommentCommand createProductCommentCommand) {
        final Product product = findById(id);

        final ProductComment productComment = new ProductComment();
        productComment.setText(createProductCommentCommand.getText());
        productComment.setCreationDate(LocalDateTime.now());
        productComment.setProduct(product);

        return productCommentRepository.save(productComment);
    }

}
