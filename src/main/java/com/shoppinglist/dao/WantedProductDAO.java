package com.shoppinglist.dao;

import com.shoppinglist.model.database.WantedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WantedProductDAO extends JpaRepository<WantedProduct, Long> {
}
