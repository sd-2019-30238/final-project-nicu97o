package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.Address;
import com.shoppinglist.model.database.AddressCoordinates;
import com.shoppinglist.model.dto.AddressCoordinatesDTO;
import com.shoppinglist.model.dto.AddressDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressMapper implements Mapper<Address, AddressDTO> {
    private Mapper<AddressCoordinates, AddressCoordinatesDTO> mapper;

    @Autowired
    public AddressMapper(Mapper<AddressCoordinates, AddressCoordinatesDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Address convertToEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setNumber(addressDTO.getNumber());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setStreet(addressDTO.getStreet());
        address.setAddressCoordinates(Optional.ofNullable(addressDTO).map(AddressDTO::getAddressCoordinates).map(mapper::convertToEntity).orElse(new AddressCoordinates()));
        return address;
    }

    @Override
    public AddressDTO convertToDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setCity(address.getCity());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setAddressCoordinates(Optional.ofNullable(address).map(Address::getAddressCoordinates).map(mapper::convertToDTO).orElse(new AddressCoordinatesDTO()));
        return addressDTO;
    }
}
