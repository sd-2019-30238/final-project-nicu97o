package com.shoppinglist.controller;

import com.shoppinglist.facade.UserServiceFacade;
import com.shoppinglist.facade.WantedProductServiceFacade;
import com.shoppinglist.model.database.Address;
import com.shoppinglist.model.database.Category;
import com.shoppinglist.model.database.ConstraintsType;
import com.shoppinglist.model.dto.AddressDTO;
import com.shoppinglist.model.dto.ProductConstraintDTO;
import com.shoppinglist.model.dto.WantedProductDTO;
import com.shoppinglist.model.mapper.Mapper;
import com.shoppinglist.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wantedProducts")
public class WantedProductController {
    private WantedProductServiceFacade wantedProductServiceFacade;
    private UserServiceFacade userServiceFacade;
    private GoogleMapsService googleMapsService;
    private Mapper<Address, AddressDTO> addressMapper;

    @Autowired
    public WantedProductController(WantedProductServiceFacade wantedProductServiceFacade, UserServiceFacade userServiceFacade, GoogleMapsService googleMapsService, Mapper<Address, AddressDTO> addressMapper) {
        this.wantedProductServiceFacade = wantedProductServiceFacade;
        this.userServiceFacade = userServiceFacade;
        this.googleMapsService = googleMapsService;
        this.addressMapper = addressMapper;
    }

    @GetMapping("/{clubId}")
    public ModelAndView getWantedProductPage(@PathVariable("clubId") long clubId) {
        ModelAndView modelAndView = new ModelAndView("WantedProduct");
        WantedProductDTO wantedProductDTO = new WantedProductDTO();
        wantedProductDTO.setProductConstraintDTO(new ProductConstraintDTO());
        modelAndView.addObject("clubId", clubId);
        modelAndView.addObject("wantedProduct", wantedProductDTO);
        modelAndView.addObject("categories", Category.values());
        modelAndView.addObject("constraintTypes", ConstraintsType.values());
        return modelAndView;
    }

    @PostMapping("/{clubId}")
    public ModelAndView addWantedProduct(@PathVariable("clubId") long clubId, @RequestParam("address") String address, @Valid WantedProductDTO wantedProductDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/clubs/" + clubId);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("redirect:/wantedProducts/" + clubId);
            redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
            return modelAndView;
        }
        if (wantedProductDTO.getProductConstraintDTO().getConstraintsType() == ConstraintsType.NONE) {
            wantedProductDTO.setProductConstraintDTO(null);
            wantedProductDTO.setConstrained(false);
        } else if (wantedProductDTO.getProductConstraintDTO().getConstraintsType() == ConstraintsType.BASED_ON_DESCRIPTION) {
            wantedProductDTO.getProductConstraintDTO().setShop(null);
            wantedProductDTO.setConstrained(true);
        } else if (wantedProductDTO.getProductConstraintDTO().getConstraintsType() == ConstraintsType.BASED_ON_LOCATION) {
            wantedProductDTO.getProductConstraintDTO().getShop().setAddress(addressMapper.convertToDTO(googleMapsService.getGeocodingResultForAnAddress(address)));
            wantedProductDTO.setConstrained(true);
        }
        wantedProductServiceFacade.addWantedProduct(clubId, wantedProductDTO, wantedProductDTO.getProductConstraintDTO());
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView markAsBought(@PathVariable("id") long id, @RequestParam("clubId") long clubId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/clubs/" + clubId);
        wantedProductServiceFacade.markAsBought(id);
        return modelAndView;
    }
}
