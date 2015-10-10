package com.zimplyshop.app.baseobjects;

import java.util.List;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZProductListObject {

    List<ZProductObject> products;
    ZProductObject header;

    public ZProductObject getHeader() {
        return header;
    }

    public void setHeader(ZProductObject header) {
        this.header = header;
    }

    public class ZProductObject {
        String name;
        String id;
        String image_url;
        int price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public List<ZProductObject> getProducts() {
        return products;
    }

    public void setProducts(List<ZProductObject> products) {
        this.products = products;
    }
}
