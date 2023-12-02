package net.croz.owasp.goodexample.controller.response;

public class OrderResponse {

    private Long id;

    private Integer quantity;

    private ProductResponse product;

    private UserResponse buyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
    }

    public UserResponse getBuyer() {
        return buyer;
    }

    public void setBuyer(UserResponse buyer) {
        this.buyer = buyer;
    }

}
