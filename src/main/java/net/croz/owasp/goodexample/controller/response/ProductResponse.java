package net.croz.owasp.goodexample.controller.response;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageName;

    private List<ProductCommentResponse> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<ProductCommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<ProductCommentResponse> comments) {
        this.comments = comments;
    }

}
