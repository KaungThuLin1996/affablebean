package com.shop.ecommerce.affablebean.model;

import java.io.Serializable;

/**
 * This class is related with View Layer
 */
public class CartItem implements Serializable {

    private long id;
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
