package com.shoppinglist.controller;

import com.shoppinglist.facade.ClubServiceFacade;
import com.shoppinglist.facade.UserServiceFacade;
import com.shoppinglist.facade.WantedProductServiceFacade;
import com.shoppinglist.model.dto.ClubDTO;
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
@RequestMapping("/clubs")
public class ClubController {
    private ClubServiceFacade clubServiceFacade;
    private WantedProductServiceFacade wantedProductServiceFacade;
    private UserServiceFacade userServiceFacade;

    @Autowired
    public ClubController(ClubServiceFacade clubServiceFacade, WantedProductServiceFacade wantedProductServiceFacade, UserServiceFacade userServiceFacade) {
        this.clubServiceFacade = clubServiceFacade;
        this.wantedProductServiceFacade = wantedProductServiceFacade;
        this.userServiceFacade = userServiceFacade;
    }

    @GetMapping("/currentUser")
    public ModelAndView getClubsPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("Club");
        modelAndView.addObject("clubs", clubServiceFacade.getClubsByUsersUsername(authentication.getName()));
        modelAndView.addObject("newClub", new ClubDTO());
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView getClubDetails(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("ClubDetails");
        ClubDTO clubDTO = clubServiceFacade.getClubById(id);
        modelAndView.addObject("members", userServiceFacade.getUsersOfAClubByClubsId(id));
        modelAndView.addObject("club", clubDTO);
        modelAndView.addObject("wantedProducts", wantedProductServiceFacade.getWantedProductsByClubId(id));
        return modelAndView;
    }

    @PutMapping("/join")
    public ModelAndView joinClub(@RequestParam("code") String code, Authentication authentication) {
        clubServiceFacade.joinClub(code, authentication.getName());
        return new ModelAndView("redirect:/clubs/currentUser");
    }

    @PostMapping
    public ModelAndView addClub(@Valid ClubDTO newClub, BindingResult bindingResult, Authentication authentication, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/clubs/currentUser");
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
            return modelAndView;
        }
        clubServiceFacade.addClub(newClub, authentication.getName());
        return modelAndView;
    }
}
