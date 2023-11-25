package net.croz.owasp.goodexample.mapper;

import net.croz.owasp.goodexample.controller.response.ProductCommentResponse;
import net.croz.owasp.goodexample.controller.response.UserResponse;
import net.croz.owasp.goodexample.entity.AuthUser;
import net.croz.owasp.goodexample.entity.ProductComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCommentProductCommentResponseMapper
    implements CreateMapper<ProductComment, ProductCommentResponse> {

    private final CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper;

    @Autowired
    public ProductCommentProductCommentResponseMapper(
        CreateMapper<AuthUser, UserResponse> authUserUserResponseCreateMapper) {
        this.authUserUserResponseCreateMapper = authUserUserResponseCreateMapper;
    }

    @Override
    public ProductCommentResponse map(ProductComment productComment) {
        final ProductCommentResponse productCommentResponse = new ProductCommentResponse();

        productCommentResponse.setId(productComment.getId());
        productCommentResponse.setText(productComment.getText());
        productCommentResponse.setCreationDate(productComment.getCreationDate());
        productCommentResponse.setCreator(authUserUserResponseCreateMapper.map(productComment.getCreator()));

        return productCommentResponse;
    }

}
