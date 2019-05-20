package com.shoppinglist.facade;

import com.shoppinglist.model.dto.ProductConstraintDTO;

public interface ProductConstraintServiceFacade {
    ProductConstraintDTO getProductConstraintById(long id);
}
