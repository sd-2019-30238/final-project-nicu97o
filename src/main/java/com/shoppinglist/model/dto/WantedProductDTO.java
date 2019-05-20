package com.shoppinglist.model.dto;

import com.shoppinglist.model.database.Category;
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
public class WantedProductDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private boolean bought;

    private boolean constrained;

    private Category category;

    private ProductConstraintDTO productConstraintDTO;
}
