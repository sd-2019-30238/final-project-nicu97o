package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.AddressCoordinates;
import com.shoppinglist.model.dto.AddressCoordinatesDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressCoordinatesMapper implements Mapper<AddressCoordinates, AddressCoordinatesDTO> {
    @Override
    public AddressCoordinates convertToEntity(AddressCoordinatesDTO addressCoordinatesDTO) {
        AddressCoordinates addressCoordinates = new AddressCoordinates();
        addressCoordinates.setId(addressCoordinatesDTO.getId());
        addressCoordinates.setLatitude(addressCoordinatesDTO.getLatitude());
        addressCoordinates.setLongitude(addressCoordinatesDTO.getLongitude());
        return addressCoordinates;
    }

    @Override
    public AddressCoordinatesDTO convertToDTO(AddressCoordinates addressCoordinates) {
        AddressCoordinatesDTO addressCoordinatesDTO = new AddressCoordinatesDTO();
        addressCoordinatesDTO.setId(addressCoordinates.getId());
        addressCoordinatesDTO.setLatitude(addressCoordinates.getLatitude());
        addressCoordinatesDTO.setLongitude(addressCoordinates.getLongitude());
        return addressCoordinatesDTO;
    }
}
