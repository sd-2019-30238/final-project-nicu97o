package com.shoppinglist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvitationDTO {
    private Long id;

    @NotBlank
    private String inviteCode;

    private boolean used;

    @NotNull
    private UserDTO sender;

    @NotNull
    private UserDTO receiver;
}
