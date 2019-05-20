package com.shoppinglist.service;

import com.shoppinglist.model.database.Address;

public interface GoogleMapsService {

    Address getGeocodingResultForAnAddress(String address);
}
