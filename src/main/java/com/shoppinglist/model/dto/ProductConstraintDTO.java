package com.shoppinglist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductConstraintDTO {
    private Long id;
    private String name;
    private String message;
    private WantedProductDTO wantedProduct;
    private ShopDTO shop;
}
