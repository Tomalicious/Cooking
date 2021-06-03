package com.vdab.cooking.controllers;


import com.vdab.cooking.domain.Ingredient;
import com.vdab.cooking.domain.Units;
import com.vdab.cooking.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

@Controller
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping(value = "/addingingredient")
    public String showIngredient(Model model){
        List<Units> unitsList = Arrays.stream(Units.values()).collect(Collectors.toList());
//        List unitsTypes = new ArrayList();
//        for(Units u : unitsList){
//            unitsTypes.add(u.getMeasuringType());
//        }
//        List distinctTypes = (List) unitsTypes.stream().distinct().collect(Collectors.toList());
//

//        List<Units> unitTypes = unitsList.stream()
//                .filter(units -> units.getMeasuringType().equals("weight") || units.getMeasuringType().equals("volume"))
//                .distinct()
//                .collect(Collectors.toList());

        model.addAttribute("unitTypes" , Units.getMeasuringTypes());
        model.addAttribute("newingredient" , Ingredient.builder().build());
        return "addIngredient";
    }

    @PostMapping(value = "/saveingredient")
    public String saveIngredient(@ModelAttribute Ingredient newIngredient){
        ingredientService.saveIngredient(newIngredient);
        return "redirect:/";
    }



}
