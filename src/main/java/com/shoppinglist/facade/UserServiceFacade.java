package com.shoppinglist.facade;

import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.dto.UserDTO;

import java.util.List;

public interface UserServiceFacade {
    UserDTO getUserById(long id);

    UserDTO getUserByUsername(String username);

    List<UserDTO> getAllUsers();

    void addUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser(String username);

    void activateUser(Token token);

    void changePassword(String username, String oldPassword, String newPassword);

    void changeMail(String username, String newMail);

    List<UserDTO> getUsersOfAClubByClubsId(long id);
}
