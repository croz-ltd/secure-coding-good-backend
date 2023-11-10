package net.croz.owasp.goodexample.mapper;

import net.croz.owasp.goodexample.controller.response.ProductResponse;
import net.croz.owasp.goodexample.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductProductResponseMapper implements CreateMapper<Product, ProductResponse> {

    @Override
    public ProductResponse map(Product product) {
        final ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setImageName(product.getProductImage().getFilename());

        return productResponse;
    }

}
