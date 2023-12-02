package net.croz.owasp.goodexample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_comment")
public class ProductComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_comment_id_generator")
    @SequenceGenerator(name = "product_comment_id_generator", sequenceName = "product_comment_id_seq",
        allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "product_id",
        foreignKey = @ForeignKey(name = "fk_product_comment_product"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "creator_id",
        foreignKey = @ForeignKey(name = "fk_product_comment_user_buyer"))
    private UserBuyer creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserBuyer getCreator() {
        return creator;
    }

    public void setCreator(UserBuyer creator) {
        this.creator = creator;
    }

}
