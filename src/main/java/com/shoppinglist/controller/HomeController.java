package com.shoppinglist.controller;

import com.shoppinglist.facade.InvitationServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private InvitationServiceFacade invitationServiceFacade;

    @Autowired
    public HomeController(InvitationServiceFacade invitationServiceFacade) {
        this.invitationServiceFacade = invitationServiceFacade;
    }

    @RequestMapping({"/", "/home"})
    public ModelAndView getHomePage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("Home");
        modelAndView.addObject("invitations", invitationServiceFacade.getAllUnconfirmedInvitationsForAnUser(authentication.getName()));
        return modelAndView;
    }
}
