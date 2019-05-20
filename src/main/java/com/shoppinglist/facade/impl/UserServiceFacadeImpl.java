package com.shoppinglist.facade.impl;

import com.shoppinglist.facade.UserServiceFacade;
import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.database.User;
import com.shoppinglist.model.dto.UserDTO;
import com.shoppinglist.model.mapper.Mapper;
import com.shoppinglist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceFacadeImpl implements UserServiceFacade {
    private UserService userService;
    private Mapper<User, UserDTO> mapper;

    @Autowired
    public UserServiceFacadeImpl(UserService userService, Mapper<User, UserDTO> mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public UserDTO getUserById(long id) {
        return mapper.convertToDTO(userService.getUserById(id));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return mapper.convertToDTO(userService.getUserByUsername(username));
    }

    @Override
    public UserDTO getUserByMail(String mail) {
        return mapper.convertToDTO(userService.getUserByMail(mail));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userService.addUser(mapper.convertToEntity(userDTO));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userService.updateUser(mapper.convertToEntity(userDTO));
    }

    @Override
    public void deleteUser(String username) {
        userService.deleteUser(username);
    }

    @Override
    public void activateUser(Token token) {
        userService.activateUser(token);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
    }

    @Override
    public void changeMail(String username, String newMail) {
        userService.changeMail(username, newMail);
    }

    @Override
    public List<UserDTO> getUsersOfAClubByClubsId(long id) {
        return userService.getUsersOfAClubByClubsId(id).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void changePasswordBasedOnToken(String username, String password, String token) {
        userService.changePasswordBasedOnToken(username, password, token);
    }
}
