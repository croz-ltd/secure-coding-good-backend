package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.ProductComment;
import net.croz.owasp.goodexample.entity.ProductImage;
import net.croz.owasp.goodexample.entity.UserBuyer;
import net.croz.owasp.goodexample.entity.UserSeller;
import net.croz.owasp.goodexample.exception.EntityNotFoundException;
import net.croz.owasp.goodexample.exception.FileTypeException;
import net.croz.owasp.goodexample.repository.ProductCommentRepository;
import net.croz.owasp.goodexample.repository.ProductRepository;
import net.croz.owasp.goodexample.service.FilePreconditionService;
import net.croz.owasp.goodexample.service.ProductService;
import net.croz.owasp.goodexample.service.StorageService;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;
import net.croz.owasp.goodexample.service.command.CreateProductCommentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductCommentRepository productCommentRepository;

    private final StorageService storageService;

    private final FilePreconditionService filePreconditionService;

    @Autowired
    public ProductServiceImpl(
        ProductRepository productRepository,
        ProductCommentRepository productCommentRepository,
        StorageService storageService,
        FilePreconditionService filePreconditionService
    ) {
        this.productRepository = productRepository;
        this.productCommentRepository = productCommentRepository;
        this.storageService = storageService;
        this.filePreconditionService = filePreconditionService;
    }

    @Transactional
    @Override
    public Product create(CreateProductCommand createProductCommand, UserSeller userSeller) {
        if (!filePreconditionService.canUpload(createProductCommand.getImage())) {
            throw new FileTypeException("Invalid file type");
        }

        final ProductImage productImage = storageService.store(createProductCommand.getImage());

        final Product product = new Product();
        product.setName(createProductCommand.getName());
        product.setDescription(createProductCommand.getDescription());
        product.setPrice(createProductCommand.getPrice());
        product.setProductImage(productImage);
        product.setSeller(userSeller);

        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            Product.class, String.format("id=%s", id))
        );
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public ProductComment createComment(Long id, CreateProductCommentCommand createProductCommentCommand, UserBuyer userBuyer) {
        final Product product = findById(id);

        final ProductComment productComment = new ProductComment();
        productComment.setText(createProductCommentCommand.getText());
        productComment.setCreationDate(LocalDateTime.now());
        productComment.setProduct(product);
        productComment.setCreator(userBuyer);

        return productCommentRepository.save(productComment);
    }

}
