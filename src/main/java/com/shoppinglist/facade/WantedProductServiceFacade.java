package com.shoppinglist.facade;

import com.shoppinglist.model.dto.ProductConstraintDTO;
import com.shoppinglist.model.dto.WantedProductDTO;

import java.util.List;

public interface WantedProductServiceFacade {
    List<WantedProductDTO> getWantedProductsByClubId(long id);

    void addWantedProduct(long clubId, WantedProductDTO wantedProduct, ProductConstraintDTO productConstraintDTO);
}
