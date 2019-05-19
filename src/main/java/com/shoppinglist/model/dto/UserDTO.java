package com.shoppinglist.model.dto;

import com.shoppinglist.model.database.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotBlank(message = "Username must not be left empty!")
    @Size(min = 3, message = "Username must have minimum 3 chars!")
    @Size(max = 45, message = "Username must have maximum 45 chars!")
    private String username;

    @Email(message = "Invalid email!")
    @Size(min = 3, message = "Username must have minimum 3 chars!")
    @Size(max = 45, message = "Username must have maximum 45 chars!")
    private String mail;

    @NotBlank(message = "Password must not be left empty!")
    @Size(min = 3, message = "Password must have minimum 3 chars!")
    @Size(max = 45, message = "Password must have maximum 45 chars!")
    private String password;

    private UserType userType;
}
