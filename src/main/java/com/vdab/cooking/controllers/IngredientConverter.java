package com.vdab.cooking.controllers;

import com.vdab.cooking.domain.Ingredient;
import com.vdab.cooking.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class IngredientConverter implements Formatter<Ingredient> {
    @Autowired
    private IngredientService ingredientService;

    @Override
    public Ingredient parse(String text, Locale locale) throws ParseException {
        return ingredientService.findById(Integer.valueOf(text));
    }

    @Override
    public String print(Ingredient object, Locale locale) {
        return (object != null ? String.valueOf(object.getId()): "");
    }
}
