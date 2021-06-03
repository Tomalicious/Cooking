package com.vdab.cooking.controllers;


import com.vdab.cooking.domain.Ingredient;
import com.vdab.cooking.domain.Recipe;
import com.vdab.cooking.domain.Units;
import com.vdab.cooking.services.IngredientService;
import com.vdab.cooking.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "/recipes")
    public String showRecipeTable(Model model) {
        return "recipeTable";
    }

    @RequestMapping(value = "/addrecipe") //Kan Zowel POst als get zijn
    public String showAddRecipe(@ModelAttribute Recipe recipe, Model model, @RequestParam(value = "addRow", required = false) String addRow) {
        model.addAttribute("ingredients", ingredientService.findAll());
        model.addAttribute("units", Units.values());
        model.addAttribute("selectedIngredient" ,  Ingredient.builder().amount(0).build());
        if (addRow != null) {
            recipe.getIngredientList().add(Ingredient.builder().build());
        }else if(recipe.getName() != null){
            recipeService.save(recipe);
        }
        if(recipe != null){
            model.addAttribute("newrecipe",recipe);
        }


        return "addRecipe";
    }

}
