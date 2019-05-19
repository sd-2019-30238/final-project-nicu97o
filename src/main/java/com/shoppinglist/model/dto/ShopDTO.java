package com.shoppinglist.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShopDTO {
    private Long id;
    private String name;
    private String description;
    private AddressDTO address;
    private ProductConstraintDTO productConstraint;
}
