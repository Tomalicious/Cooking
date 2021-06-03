package com.vdab.cooking.repositories;


import com.vdab.cooking.domain.Ingredient;
import com.vdab.cooking.domain.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class RecipeRepository {
    @Value("${db.url}")
    private String url;

    @Value("${db.password}")
    private String password;

    @Value("${db.username}")
    private String username;

    public void save(Recipe recipe) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into recipes (name ,instructions, persons  ) values (?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getInstructions());
            preparedStatement.setInt(3, recipe.getPersons());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            recipe.setId(generatedKeys.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
