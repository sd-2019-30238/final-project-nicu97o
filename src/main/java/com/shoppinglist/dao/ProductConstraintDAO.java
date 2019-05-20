package com.shoppinglist.dao;

import com.shoppinglist.model.database.ProductConstraint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductConstraintDAO extends JpaRepository<ProductConstraint, Long> {
}
