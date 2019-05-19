package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.model.database.WantedProduct;
import com.shoppinglist.model.dto.ProductConstraintDTO;
import com.shoppinglist.model.dto.WantedProductDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WantedProductMapper implements Mapper<WantedProduct, WantedProductDTO> {
    private Mapper<ProductConstraint, ProductConstraintDTO> mapper;

    @Autowired
    public WantedProductMapper(Mapper<ProductConstraint, ProductConstraintDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public WantedProduct convertToEntity(WantedProductDTO wantedProductDTO) {
        WantedProduct wantedProduct = new WantedProduct();
        wantedProduct.setId(wantedProductDTO.getId());
        wantedProduct.setName(wantedProductDTO.getName());
        wantedProduct.setDescription(wantedProductDTO.getDescription());
        wantedProduct.setCategory(wantedProductDTO.getCategory());
        wantedProduct.setBought(wantedProductDTO.isBought());
        wantedProduct.setProductConstraint(Optional.ofNullable(wantedProductDTO.getProductConstraintDTO()).map(mapper::convertToEntity).orElse(new ProductConstraint()));
        return wantedProduct;
    }

    @Override
    public WantedProductDTO convertToDTO(WantedProduct wantedProduct) {
        WantedProductDTO wantedProductDTO = new WantedProductDTO();
        wantedProductDTO.setId(wantedProduct.getId());
        wantedProductDTO.setName(wantedProduct.getName());
        wantedProductDTO.setDescription(wantedProduct.getDescription());
        wantedProductDTO.setCategory(wantedProduct.getCategory());
        wantedProductDTO.setBought(wantedProduct.isBought());
        wantedProductDTO.setProductConstraintDTO(Optional.ofNullable(wantedProduct.getProductConstraint()).map(mapper::convertToDTO).orElse(new ProductConstraintDTO()));
        return wantedProductDTO;
    }
}
