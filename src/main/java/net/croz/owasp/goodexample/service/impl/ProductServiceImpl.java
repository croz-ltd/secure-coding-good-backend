package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.ProductImage;
import net.croz.owasp.goodexample.repository.ProductRepository;
import net.croz.owasp.goodexample.service.ProductService;
import net.croz.owasp.goodexample.service.StorageService;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StorageService storageService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StorageService storageService) {
        this.productRepository = productRepository;
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

}
