package net.croz.owasp.goodexample.mapper;

import net.croz.owasp.goodexample.controller.response.ProductCommentResponse;
import net.croz.owasp.goodexample.controller.response.ProductResponse;
import net.croz.owasp.goodexample.controller.response.UserResponse;
import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.ProductComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProductResponseMapper implements CreateMapper<Product, ProductResponse> {

    private final CreateMapper<ProductComment, ProductCommentResponse> productCommentProductCommentResponseCreateMapper;
    private final CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper;

    @Autowired
    public ProductProductResponseMapper(
        CreateMapper<ProductComment, ProductCommentResponse> productCommentProductCommentResponseCreateMapper,
        CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper) {
        this.productCommentProductCommentResponseCreateMapper = productCommentProductCommentResponseCreateMapper;
        this.authUserUserResponseCreateMapper = authUserUserResponseCreateMapper;
    }

    @Override
    public ProductResponse map(Product product) {
        final ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setImageName(product.getProductImage().getFilename());
        productResponse.setComments(
            productCommentProductCommentResponseCreateMapper.mapToList(product.getProductComments()));
        productResponse.setSeller(authUserUserResponseCreateMapper.map(product.getSeller()));

        return productResponse;
    }

}
