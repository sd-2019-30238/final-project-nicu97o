package com.shoppinglist.service;

import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.model.database.WantedProduct;

import java.util.List;

public interface WantedProductService {
    WantedProduct getWantedProductById(long id);

    List<WantedProduct> getWantedProductsByClubId(long id);

    void markAsBought(long id);

    void addWantedProduct(long clubId, WantedProduct wantedProduct, ProductConstraint productConstraint);
}
