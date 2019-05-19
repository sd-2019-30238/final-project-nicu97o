package com.shoppinglist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubDTO {
    private Long id;

    @NotBlank
    private String name;

    private String inviteCode;

    private List<WantedProductDTO> wantedProducts = new ArrayList<>();

    private List<UserDTO> users = new ArrayList<>();
}
