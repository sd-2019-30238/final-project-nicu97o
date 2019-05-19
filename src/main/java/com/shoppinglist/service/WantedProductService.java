package com.shoppinglist.service;

import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.model.database.WantedProduct;

import java.util.List;

public interface WantedProductService {
    List<WantedProduct> getWantedProductsByClubId(long id);

    void addWantedProduct(long clubId, WantedProduct wantedProduct, ProductConstraint productConstraint);
}
