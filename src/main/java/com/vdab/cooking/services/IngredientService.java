package com.vdab.cooking.services;

import com.vdab.cooking.domain.Ingredient;
import com.vdab.cooking.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public void saveIngredient(Ingredient newIngredient) {
        ingredientRepository.saveIngredient(newIngredient);
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(Integer id) {
        return ingredientRepository.findById(id);
    }
}
