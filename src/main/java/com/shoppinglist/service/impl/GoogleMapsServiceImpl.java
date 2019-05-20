package com.shoppinglist.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.shoppinglist.converter.GeocodingResultConverter;
import com.shoppinglist.exception.NoAddressFoundException;
import com.shoppinglist.model.database.Address;
import com.shoppinglist.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsServiceImpl implements GoogleMapsService {
    private GeoApiContext geoApiContext;
    private GeocodingResultConverter geocodingResultConverter;

    @Autowired
    public GoogleMapsServiceImpl(GeoApiContext geoApiContext, GeocodingResultConverter geocodingResultConverter) {
        this.geoApiContext = geoApiContext;
        this.geocodingResultConverter = geocodingResultConverter;
    }

    @Override
    public Address getGeocodingResultForAnAddress(String address) {
        GeocodingResult[] geocodingResults = null;
        try {
            geocodingResults = GeocodingApi.geocode(geoApiContext, address).await();
        } catch (ApiException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (geocodingResults == null) {
            throw new NoAddressFoundException("No address found for input : " + address);
        }
        if (geocodingResults.length > 1) {
            throw new NoAddressFoundException("Couldn't find a single address for input : " + address);
        }
        return geocodingResultConverter.convert(geocodingResults[0]);
    }


}