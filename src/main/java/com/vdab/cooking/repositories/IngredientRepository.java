package com.vdab.cooking.repositories;


import com.vdab.cooking.domain.Ingredient;
import com.vdab.cooking.domain.Recipe;
import com.vdab.cooking.domain.Units;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class IngredientRepository {

    @Value("${db.url}")
    private String url;

    @Value("${db.password}")
    private String password;

    @Value("${db.username}")
    private String username;


    public void saveIngredient(Ingredient newIngredient) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ingredients (name , measuring_type  ) values (?,?) ");
            preparedStatement.setString(1, newIngredient.getName());
            preparedStatement.setString(2, newIngredient.getUnit().getMeasuringType());
            preparedStatement.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ingredient> findAll() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ingredients ");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Ingredient> ingredientList = new ArrayList<>();
            while (resultSet.next()) {
                String measuringType = resultSet.getString("measuring_type");
                Units unit = Arrays.stream(Units.values()).filter(units -> units.getMeasuringType().equals(measuringType)).findFirst().get();
                ingredientList.add(Ingredient.builder().id(resultSet.getInt("id"))
                        .unit(unit).name(resultSet.getString("name")).build());
            }
            // dit is wat we gaan oproepen via builder
            return ingredientList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ingredient findById(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ingredients where id = ? ");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            resultSet.next();
            String measuringType = resultSet.getString("measuring_type");
            Units unit = Arrays.stream(Units.values()).filter(units -> units.getMeasuringType().equals(measuringType)).findFirst().get();
            return Ingredient.builder()
                    .id(resultSet.getInt("id"))
                    .unit(unit)
                    .name(resultSet.getString("name"))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveIngredientForRecipe(Ingredient ingredient, Recipe recipe) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into food_ingredients_join_table (ingredient_fk , recipe_fk, amount  ) values (?,?,?) ");
            preparedStatement.setInt(1, ingredient.getId());
            preparedStatement.setInt(2, recipe.getId());
            preparedStatement.setDouble(3, ingredient.getAmount());
            preparedStatement.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
