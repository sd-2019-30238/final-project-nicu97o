package com.shoppinglist.dao;

import com.shoppinglist.model.database.WantedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WantedProductDAO extends JpaRepository<WantedProduct, Long> {
    List<WantedProduct> findWantedProductsByClubId(long id);

    List<WantedProduct> findWantedProductsByClubIdAndBoughtFalse(long id);

    List<WantedProduct> findWantedProductsByClubIdAndBoughtTrue(long id);
}
