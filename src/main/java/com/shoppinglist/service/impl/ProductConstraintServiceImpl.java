package com.shoppinglist.service.impl;

import com.shoppinglist.dao.ProductConstraintDAO;
import com.shoppinglist.exception.NoProductConstraintFoundException;
import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.service.ProductConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductConstraintServiceImpl implements ProductConstraintService {
    ProductConstraintDAO productConstraintDAO;

    @Autowired
    public ProductConstraintServiceImpl(ProductConstraintDAO productConstraintDAO) {
        this.productConstraintDAO = productConstraintDAO;
    }

    @Override
    public ProductConstraint getProductConstraintById(long id) {
        return productConstraintDAO.findById(id).orElseThrow(() -> new NoProductConstraintFoundException());
    }
}
