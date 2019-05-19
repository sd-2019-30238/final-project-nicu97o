package com.shoppinglist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressCoordinatesDTO {
    private Long id;
    private double latitude;
    private double longitude;
}
