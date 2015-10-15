package com.zimplyshop.app.baseobjects;

import java.util.List;

/**
 * Created by praveen goel on 10/14/2015.
 */
public class ZCartListObject {

    List<ZCartItem> cart_items;

    public List<ZCartItem> getCart_items() {
        return cart_items;
    }

    public void setCart_items(List<ZCartItem> cart_items) {
        this.cart_items = cart_items;
    }

    public class ZCartItem {
        String name;
        int quantity;
        float price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }
}
