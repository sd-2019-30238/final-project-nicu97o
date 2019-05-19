package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.User;
import com.shoppinglist.model.dto.UserDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setMail(userDTO.getMail());
        user.setUserType(userDTO.getUserType());
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setMail(user.getMail());
        userDTO.setUserType(user.getUserType());
        return userDTO;
    }
}
