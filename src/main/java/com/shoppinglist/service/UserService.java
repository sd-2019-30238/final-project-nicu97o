package com.shoppinglist.service;

import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.database.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(String username);

    void activateUser(Token token);

    void changePassword(String username, String oldPassword, String newPassword);

    void changeMail(String username, String newMail);

    List<User> getUsersOfAClubByClubsId(long id);
}
