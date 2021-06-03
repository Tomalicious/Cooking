package com.vdab.cooking.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe extends BaseEntity{

    private String name;
    private List<Ingredient> ingredientList;
    private String instructions;
    private int persons;

}
