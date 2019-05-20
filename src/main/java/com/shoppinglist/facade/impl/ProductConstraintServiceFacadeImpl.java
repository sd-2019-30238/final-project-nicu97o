package com.shoppinglist.facade.impl;

import com.shoppinglist.facade.ProductConstraintServiceFacade;
import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.model.dto.ProductConstraintDTO;
import com.shoppinglist.model.mapper.Mapper;
import com.shoppinglist.service.ProductConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConstraintServiceFacadeImpl implements ProductConstraintServiceFacade {
    private Mapper<ProductConstraint, ProductConstraintDTO> mapper;
    private ProductConstraintService productConstraintService;

    @Autowired
    public ProductConstraintServiceFacadeImpl(Mapper<ProductConstraint, ProductConstraintDTO> mapper, ProductConstraintService productConstraintService) {
        this.mapper = mapper;
        this.productConstraintService = productConstraintService;
    }

    @Override
    public ProductConstraintDTO getProductConstraintById(long id) {
        return mapper.convertToDTO(productConstraintService.getProductConstraintById(id));
    }
}
