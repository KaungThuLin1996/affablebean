package com.shop.ecommerce.affablebean.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderedProductId implements Serializable {

    private Long customerOrderId;
    private Long productId;

    public OrderedProductId() {
    }

    public OrderedProductId(Long customerOrderId, Long productId) {
        this.customerOrderId = customerOrderId;
        this.productId = productId;
    }

    public Long getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Long customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
