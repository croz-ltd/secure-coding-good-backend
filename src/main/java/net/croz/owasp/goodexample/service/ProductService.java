package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.ProductComment;
import net.croz.owasp.goodexample.entity.UserBuyer;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;
import net.croz.owasp.goodexample.service.command.CreateProductCommentCommand;

import java.util.List;

public interface ProductService {

    Product create(CreateProductCommand createProductCommand);

    Product findById(Long id);

    List<Product> findAll();

    ProductComment createComment(Long id, CreateProductCommentCommand createProductCommentCommand, UserBuyer userBuyer);

}
