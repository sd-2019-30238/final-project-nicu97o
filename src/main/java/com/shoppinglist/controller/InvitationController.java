package com.shoppinglist.controller;

import com.shoppinglist.facade.ClubServiceFacade;
import com.shoppinglist.facade.InvitationServiceFacade;
import com.shoppinglist.model.dto.ClubDTO;
import com.shoppinglist.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/invitations")
public class InvitationController {
    private InvitationServiceFacade invitationServiceFacade;
    private ClubServiceFacade clubServiceFacade;
    private EmailService emailService;

    @Autowired
    public InvitationController(InvitationServiceFacade invitationServiceFacade, ClubServiceFacade clubServiceFacade, EmailService emailService) {
        this.invitationServiceFacade = invitationServiceFacade;
        this.clubServiceFacade = clubServiceFacade;
        this.emailService = emailService;
    }

    @PostMapping("/username/{clubId}")
    public ModelAndView inviteUserByUsername(@PathVariable("clubId") long clubId, @RequestParam("username") String username, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/clubs/" + clubId);
        invitationServiceFacade.sendInvitationByUsername(clubId, authentication.getName(), username);
        return modelAndView;
    }

    @PostMapping("/mail/{clubId}")
    public ModelAndView inviteUserByMail(@PathVariable("clubId") long clubId, @RequestParam("mail") String mail, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/clubs/" + clubId);
        invitationServiceFacade.sendInvitationByMail(clubId, authentication.getName(), mail);
        return modelAndView;
    }

    @GetMapping("/use/{id}")
    public ModelAndView useInvitation(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/clubs/currentUser");
        invitationServiceFacade.useInvitation(id);
        return modelAndView;
    }
}
