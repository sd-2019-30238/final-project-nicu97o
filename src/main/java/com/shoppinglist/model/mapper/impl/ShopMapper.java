package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.Address;
import com.shoppinglist.model.database.Shop;
import com.shoppinglist.model.dto.AddressDTO;
import com.shoppinglist.model.dto.ShopDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShopMapper implements Mapper<Shop, ShopDTO> {
    private Mapper<Address, AddressDTO> mapper;

    @Autowired
    public ShopMapper(Mapper<Address, AddressDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Shop convertToEntity(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(shopDTO.getId());
        shop.setName(shopDTO.getName());
        shop.setDescription(shopDTO.getDescription());
        shop.setAddress(Optional.ofNullable(shopDTO.getAddress()).map(mapper::convertToEntity).orElse(new Address()));
        return shop;
    }

    @Override
    public ShopDTO convertToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setDescription(shop.getDescription());
        shopDTO.setAddress(Optional.ofNullable(shop.getAddress()).map(mapper::convertToDTO).orElse(new AddressDTO()));
        return shopDTO;
    }
}
