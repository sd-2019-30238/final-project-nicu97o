package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.model.database.Shop;
import com.shoppinglist.model.database.WantedProduct;
import com.shoppinglist.model.dto.ProductConstraintDTO;
import com.shoppinglist.model.dto.ShopDTO;
import com.shoppinglist.model.dto.WantedProductDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConstraintMapper implements Mapper<ProductConstraint, ProductConstraintDTO> {
    private Mapper<Shop, ShopDTO> mapper;

    @Autowired
    public ProductConstraintMapper(Mapper<Shop, ShopDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public ProductConstraint convertToEntity(ProductConstraintDTO productConstraintDTO) {
        if (productConstraintDTO == null) {
            return null;
        }
        ProductConstraint productConstraint = new ProductConstraint();
        productConstraint.setId(productConstraintDTO.getId());
        productConstraint.setConstraintsType(productConstraintDTO.getConstraintsType());
        productConstraint.setName(productConstraintDTO.getName());
        productConstraint.setMessage(productConstraintDTO.getMessage());
        productConstraint.setShop(Optional.ofNullable(productConstraintDTO).map(ProductConstraintDTO::getShop).map(mapper::convertToEntity).orElse(null));
        return productConstraint;
    }

    @Override
    public ProductConstraintDTO convertToDTO(ProductConstraint productConstraint) {
        if (productConstraint == null) {
            return null;
        }
        ProductConstraintDTO productConstraintDTO = new ProductConstraintDTO();
        productConstraintDTO.setId(productConstraint.getId());
        productConstraintDTO.setName(productConstraint.getName());
        productConstraintDTO.setMessage(productConstraint.getMessage());
        productConstraintDTO.setConstraintsType(productConstraint.getConstraintsType());
        productConstraintDTO.setShop(Optional.ofNullable(productConstraint).map(ProductConstraint::getShop).map(mapper::convertToDTO).orElse(new ShopDTO()));
        return productConstraintDTO;
    }
}
