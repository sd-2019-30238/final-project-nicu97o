package com.shoppinglist.controller;

import com.shoppinglist.facade.UserServiceFacade;
import com.shoppinglist.facade.WantedProductServiceFacade;
import com.shoppinglist.model.database.Category;
import com.shoppinglist.model.database.ConstraintsType;
import com.shoppinglist.model.dto.ProductConstraintDTO;
import com.shoppinglist.model.dto.UserDTO;
import com.shoppinglist.model.dto.WantedProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wantedProducts")
public class WantedProductController {
    private WantedProductServiceFacade wantedProductServiceFacade;
    private UserServiceFacade userServiceFacade;

    @Autowired
    public WantedProductController(WantedProductServiceFacade wantedProductServiceFacade, UserServiceFacade userServiceFacade) {
        this.wantedProductServiceFacade = wantedProductServiceFacade;
        this.userServiceFacade = userServiceFacade;
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
    public ModelAndView addWantedProduct(@PathVariable("clubId") long clubId, @Valid WantedProductDTO wantedProductDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/clubs/" + clubId);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("redirect:/wantedProducts/" + clubId);
            redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
            return modelAndView;
        }
        wantedProductServiceFacade.addWantedProduct(clubId, wantedProductDTO, wantedProductDTO.getProductConstraintDTO());
        return modelAndView;
    }
}
