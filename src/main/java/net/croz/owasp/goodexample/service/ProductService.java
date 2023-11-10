package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;

public interface ProductService {

    Product create(CreateProductCommand createProductCommand);

}
