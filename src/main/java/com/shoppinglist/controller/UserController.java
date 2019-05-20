package com.shoppinglist.controller;

import com.shoppinglist.facade.UserServiceFacade;
import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.database.TokenType;
import com.shoppinglist.model.database.UserType;
import com.shoppinglist.model.dto.UserDTO;
import com.shoppinglist.service.EmailService;
import com.shoppinglist.service.TokenService;
import com.shoppinglist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceFacade userServiceFacade;
    private EmailService emailService;
    private TokenService tokenService;

    @Autowired
    public UserController(UserServiceFacade userServiceFacade, EmailService emailService, TokenService tokenService) {
        this.userServiceFacade = userServiceFacade;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("Register");
        modelAndView.addObject("userTypes", UserType.values());
        modelAndView.addObject("user", new UserDTO());
        return modelAndView;
    }

    @GetMapping("/accountDetails")
    public ModelAndView getAccountDetailsPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("AccountDetails");
        modelAndView.addObject("user", userServiceFacade.getUserByUsername(authentication.getName()));
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) throws MessagingException {
        ModelAndView modelAndView = new ModelAndView("login");
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("redirect:/users/register");
            redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
            return modelAndView;
        }
        userServiceFacade.addUser(userDTO);
        Token token = tokenService.createToken(userDTO.getUsername(), TokenType.ACTIVATION);
        String activationLink = "http://" + httpServletRequest.getHeader("host") + "/users/activate?token=" + token.getTokenValue();
        emailService.sendMail(userDTO.getMail(), "Account register confirmation", activationLink);
        return modelAndView;
    }

    @GetMapping("/activate")
    public ModelAndView activateUser(@RequestParam("token") String token) throws MessagingException {
        Token tokenToActivate = tokenService.getToken(token);
        userServiceFacade.activateUser(tokenToActivate);
        emailService.sendMail(tokenToActivate.getUser().getMail(), "Account confirmation", "Account activated!");
        return new ModelAndView("redirect:/login");
    }


    @DeleteMapping
    public ModelAndView deleteAccount(Authentication authentication) {
        userServiceFacade.deleteUser(authentication.getName());
        return new ModelAndView("redirect:/login");
    }

    @PutMapping("/mail")
    public ModelAndView updateMail(@RequestParam("mail") String mail, Authentication authentication) {
        userServiceFacade.changeMail(authentication.getName(), mail);
        return new ModelAndView("redirect:/users/accountDetails");
    }

    @PutMapping("/password")
    public ModelAndView updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Authentication authentication) {
        userServiceFacade.changePassword(authentication.getName(), oldPassword, newPassword);
        return new ModelAndView("redirect:/users/accountDetails");
    }

    @GetMapping("/forgotPassword")
    public ModelAndView getForgotPasswordPage() {
        return new ModelAndView("ForgotPassword");
    }

    @PostMapping("/forgotPassword")
    public ModelAndView sendForgotPasswordMail(@RequestParam("mail") String mail, HttpServletRequest httpServletRequest) throws MessagingException {
        UserDTO userDTO = userServiceFacade.getUserByMail(mail);
        Token token = tokenService.createToken(userDTO.getUsername(), TokenType.PASSWORD_RESET);
        String resetPasswordLink = "http://" + httpServletRequest.getHeader("host") + "/users/changePassword?token=" + token.getTokenValue() + "&username=" + userDTO.getUsername();
        emailService.sendMail(userDTO.getMail(), "Reset password", resetPasswordLink);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/changePassword")
    public ModelAndView getChangePassword(@RequestParam("token") String token, @RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView("ChangePassword");
        modelAndView.addObject("token", token);
        modelAndView.addObject("username", username);
        return modelAndView;
    }

    @PutMapping("/changePassword")
    public ModelAndView changePasswordWithToken(@RequestParam("token") String token, @RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        userServiceFacade.changePasswordBasedOnToken(username, password, token);
        return modelAndView;
    }
}
