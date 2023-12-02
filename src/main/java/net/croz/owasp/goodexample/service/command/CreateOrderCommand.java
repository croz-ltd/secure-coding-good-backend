package net.croz.owasp.goodexample.service.command;

import javax.validation.constraints.Min;

public class CreateOrderCommand {

    @Min(value = 1)
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
