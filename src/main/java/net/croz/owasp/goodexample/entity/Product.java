package net.croz.owasp.goodexample.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_image_id", referencedColumnName = "id")
    private ProductImage productImage;

    @OneToMany
    @JoinColumn(name = "product_id",
        foreignKey = @ForeignKey(name = "fk_product_comment_product"))
    private List<ProductComment> productComments;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserSeller seller;

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

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public List<ProductComment> getProductComments() {
        if (this.productComments == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(this.productComments);
        }

    }

    public void setProductComments(List<ProductComment> productComments) {
        if (this.productComments == null) {
            this.productComments = new ArrayList<>(productComments);
        } else {
            this.productComments.clear();
            this.productComments.addAll(productComments);
        }

    }

    public UserSeller getSeller() {
        return seller;
    }

    public void setSeller(UserSeller seller) {
        this.seller = seller;
    }

}
