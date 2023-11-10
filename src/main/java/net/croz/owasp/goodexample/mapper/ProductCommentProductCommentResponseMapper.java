package net.croz.owasp.goodexample.mapper;

import net.croz.owasp.goodexample.controller.response.ProductCommentResponse;
import net.croz.owasp.goodexample.entity.ProductComment;
import org.springframework.stereotype.Component;

@Component
public class ProductCommentProductCommentResponseMapper
    implements CreateMapper<ProductComment, ProductCommentResponse> {

    @Override
    public ProductCommentResponse map(ProductComment productComment) {
        final ProductCommentResponse productCommentResponse = new ProductCommentResponse();

        productCommentResponse.setId(productComment.getId());
        productCommentResponse.setText(productComment.getText());
        productCommentResponse.setCreationDate(productComment.getCreationDate());

        return productCommentResponse;
    }

}
