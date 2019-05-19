package com.shoppinglist.service.impl;

import com.shoppinglist.dao.UserDAO;
import com.shoppinglist.exception.IncorrectPasswordException;
import com.shoppinglist.exception.NoUserFoundException;
import com.shoppinglist.exception.TokenExpiredException;
import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.database.User;
import com.shoppinglist.service.ClubService;
import com.shoppinglist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;
    private ClubService clubService;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder, @Lazy ClubService clubService) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.clubService = clubService;
    }

    @Override
    public User getUserById(long id) {
        return userDAO.findById(id).orElseThrow(() -> new NoUserFoundException("No user with id " + id + " found!"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.findUserByUsername(username).orElseThrow(() -> new NoUserFoundException("No user with username " + username + " found!"));
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public void updateUser(User user) {
        User oldUser = getUserById(user.getId());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setMail(user.getMail());
        userDAO.save(oldUser);
    }

    @Override
    public void deleteUser(String username) {
        userDAO.delete(getUserByUsername(username));
    }

    @Override
    public void activateUser(Token token) {
        if (token.getExpireDateTime().isBefore(LocalDateTime.now()) || token.isUsed()) {
            throw new TokenExpiredException();
        }
        User user = token.getUser();
        user.setConfirmed(true);
        token.setUsed(true);
        userDAO.save(user);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = getUserByUsername(username);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userDAO.save(user);
    }

    @Override
    public void changeMail(String username, String newMail) {
        User user = getUserByUsername(username);
        user.setMail(newMail);
        userDAO.save(user);
    }

    @Override
    public List<User> getUsersOfAClubByClubsId(long id) {
        return clubService.getClubById(id).getUsers();
    }
}
