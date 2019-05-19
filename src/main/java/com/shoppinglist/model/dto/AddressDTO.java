package com.shoppinglist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String city;
    private String street;
    private Integer number;
    private String postalCode;
    private AddressCoordinatesDTO addressCoordinates;
}
