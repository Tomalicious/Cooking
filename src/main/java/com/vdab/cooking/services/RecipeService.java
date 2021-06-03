package com.vdab.cooking.services;

import com.vdab.cooking.domain.Recipe;
import com.vdab.cooking.repositories.IngredientRepository;
import com.vdab.cooking.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
        recipe.getIngredientList().forEach(ingredient -> {
            ingredientRepository.saveIngredientForRecipe(ingredient, recipe);
        });
    }
}
