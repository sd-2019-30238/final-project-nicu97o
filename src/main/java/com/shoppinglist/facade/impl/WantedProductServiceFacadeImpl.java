package com.shoppinglist.facade.impl;

import com.shoppinglist.facade.WantedProductServiceFacade;
import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.model.database.WantedProduct;
import com.shoppinglist.model.dto.ProductConstraintDTO;
import com.shoppinglist.model.dto.WantedProductDTO;
import com.shoppinglist.model.mapper.Mapper;
import com.shoppinglist.service.WantedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WantedProductServiceFacadeImpl implements WantedProductServiceFacade {
    private Mapper<WantedProduct, WantedProductDTO> mapper;
    private WantedProductService wantedProductService;
    private Mapper<ProductConstraint, ProductConstraintDTO> productConstraintMapper;

    @Autowired
    public WantedProductServiceFacadeImpl(Mapper<WantedProduct, WantedProductDTO> mapper, WantedProductService wantedProductService, Mapper<ProductConstraint, ProductConstraintDTO> productConstraintMapper) {
        this.mapper = mapper;
        this.wantedProductService = wantedProductService;
        this.productConstraintMapper = productConstraintMapper;
    }

    @Override
    public List<WantedProductDTO> getWantedProductsByClubId(long id) {
        return wantedProductService.getWantedProductsByClubId(id).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void addWantedProduct(long clubId, WantedProductDTO wantedProduct, ProductConstraintDTO productConstraintDTO) {
        wantedProductService.addWantedProduct(clubId, mapper.convertToEntity(wantedProduct), productConstraintMapper.convertToEntity(productConstraintDTO));
    }

    @Override
    public WantedProductDTO getWantedProductById(long id) {
        return mapper.convertToDTO(wantedProductService.getWantedProductById(id));
    }

    @Override
    public void markAsBought(long id) {
        wantedProductService.markAsBought(id);
    }

    @Override
    public List<WantedProductDTO> getWantedProductsByClubIdAndBoughtTrue(long id) {
        return wantedProductService.getWantedProductsByClubIdAndBoughtTrue(id).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<WantedProductDTO> getWantedProductsByClubIdAndBoughtFalse(long id) {
        return wantedProductService.getWantedProductsByClubIdAndBoughtFalse(id).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
