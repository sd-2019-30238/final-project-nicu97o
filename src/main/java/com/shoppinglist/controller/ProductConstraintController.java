package com.shoppinglist.controller;

import com.shoppinglist.facade.ProductConstraintServiceFacade;
import com.shoppinglist.model.database.ConstraintsType;
import com.shoppinglist.model.dto.ProductConstraintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/productConstraints")
public class ProductConstraintController {
    private ProductConstraintServiceFacade productConstraintServiceFacade;

    @Autowired
    public ProductConstraintController(ProductConstraintServiceFacade productConstraintServiceFacade) {
        this.productConstraintServiceFacade = productConstraintServiceFacade;
    }

    @GetMapping("/{id}")
    public ModelAndView getProductConstraintPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("ProductConstraint");
        modelAndView.addObject("types", ConstraintsType.values());
        modelAndView.addObject("productConstraint", productConstraintServiceFacade.getProductConstraintById(id));
        return modelAndView;
    }

    @GetMapping("/maps/{id}")
    public ModelAndView getMap(@PathVariable("id") long id) {
        ProductConstraintDTO productConstraint = productConstraintServiceFacade.getProductConstraintById(id);
        String url = "https://www.google.com/maps/search/?api=1&query=";
        url += productConstraint.getShop().getAddress().getAddressCoordinates().getLatitude() + "," + productConstraint.getShop().getAddress().getAddressCoordinates().getLongitude();
        return new ModelAndView("redirect:" + url);
    }

}
