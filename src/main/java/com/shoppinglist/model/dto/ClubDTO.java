package com.shoppinglist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubDTO {
    private Long id;
    private String name;
    private List<WantedProductDTO> wantedProducts = new ArrayList<>();
    private Set<UserDTO> users = new HashSet<>();
}
