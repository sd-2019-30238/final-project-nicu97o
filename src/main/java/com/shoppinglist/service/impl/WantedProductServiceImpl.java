package com.shoppinglist.service.impl;

import com.shoppinglist.dao.WantedProductDAO;
import com.shoppinglist.model.database.Club;
import com.shoppinglist.model.database.ProductConstraint;
import com.shoppinglist.model.database.WantedProduct;
import com.shoppinglist.service.ClubService;
import com.shoppinglist.service.WantedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WantedProductServiceImpl implements WantedProductService {
    private WantedProductDAO wantedProductDAO;
    private ClubService clubService;

    @Autowired
    public WantedProductServiceImpl(WantedProductDAO wantedProductDAO, ClubService clubService) {
        this.wantedProductDAO = wantedProductDAO;
        this.clubService = clubService;
    }

    @Override
    public List<WantedProduct> getWantedProductsByClubId(long id) {
        return wantedProductDAO.findWantedProductsByClubId(id);
    }

    @Override
    public void addWantedProduct(long clubId, WantedProduct wantedProduct, ProductConstraint productConstraint) {
        Club club = clubService.getClubById(clubId);
        wantedProduct.setId(null);
        wantedProduct.setClub(club);
        productConstraint.setWantedProduct(wantedProduct);
        wantedProduct.setProductConstraint(productConstraint);
        wantedProductDAO.save(wantedProduct);
    }
}
