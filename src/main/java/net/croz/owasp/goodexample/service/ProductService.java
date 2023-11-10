package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.ProductComment;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;
import net.croz.owasp.goodexample.service.command.CreateProductCommentCommand;

public interface ProductService {

    Product create(CreateProductCommand createProductCommand);

    Product findById(Long id);

    ProductComment createComment(Long id, CreateProductCommentCommand createProductCommentCommand);

}
