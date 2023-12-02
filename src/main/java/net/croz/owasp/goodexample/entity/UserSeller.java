package net.croz.owasp.goodexample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user_seller")
@PrimaryKeyJoinColumn(name = "id")
public class UserSeller extends AuthUser {

    @Column(name = "oib")
    private String oib;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Product> products;

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public List<Product> getProducts() {
        if (this.products == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(this.products);
        }

    }

    public void setProduct(List<Product> products) {
        if (this.products == null) {
            this.products = new ArrayList<>(products);
        } else {
            this.products.clear();
            this.products.addAll(products);
        }

    }

}
