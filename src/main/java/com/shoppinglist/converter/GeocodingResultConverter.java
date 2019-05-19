package com.shoppinglist.converter;

import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.shoppinglist.model.database.Address;
import com.shoppinglist.model.database.AddressCoordinates;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GeocodingResultConverter implements Converter<GeocodingResult, Address> {

    @Override
    public Address convert(GeocodingResult geocodingResult) {
        Address address = new Address();
        address.setAddressCoordinates(new AddressCoordinates(null, geocodingResult.geometry.location.lat, geocodingResult.geometry.location.lng, address));
        for (AddressComponent addressComponent : geocodingResult.addressComponents) {
            if (Arrays.asList(addressComponent.types).contains(AddressComponentType.STREET_ADDRESS) ||
                    Arrays.asList(addressComponent.types).contains(AddressComponentType.ROUTE)) {
                address.setStreet(addressComponent.longName);
            } else if (Arrays.asList(addressComponent.types).contains(AddressComponentType.STREET_NUMBER)) {
                address.setNumber(Integer.parseInt(addressComponent.longName));
            } else if (Arrays.asList(addressComponent.types).contains(AddressComponentType.POSTAL_CODE)) {
                address.setPostalCode(addressComponent.longName);
            } else if (Arrays.asList(addressComponent.types).contains(AddressComponentType.LOCALITY)) {
                address.setCity(addressComponent.longName);
            }
        }
        return address;
    }
}