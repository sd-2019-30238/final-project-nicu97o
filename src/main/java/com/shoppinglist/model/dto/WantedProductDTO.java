package com.shoppinglist.model.dto;

import com.shoppinglist.model.database.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WantedProductDTO {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private List<ProductConstraintDTO> constraints = new ArrayList<>();
    private UserDTO userWhichPosted;
    private ClubDTO club;
}
