package com.vdab.cooking.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient extends BaseEntity{

    private String name;
    private double amount;
    private Units unit;

}
