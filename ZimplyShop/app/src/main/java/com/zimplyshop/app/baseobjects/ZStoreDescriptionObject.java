package com.zimplyshop.app.baseobjects;

import java.util.List;

/**
 * Created by praveen goel on 10/10/2015.
 */
public class ZStoreDescriptionObject {

    StoreHeaderObject headerData;
    List<ZProductListObject.ZProductObject> products;

    public class StoreHeaderObject {

    }

    public StoreHeaderObject getHeaderData() {
        return headerData;
    }

    public void setHeaderData(StoreHeaderObject headerData) {
        this.headerData = headerData;
    }

    public List<ZProductListObject.ZProductObject> getProducts() {
        return products;
    }

    public void setProducts(List<ZProductListObject.ZProductObject> products) {
        this.products = products;
    }
}
