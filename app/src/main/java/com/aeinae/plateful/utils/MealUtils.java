package com.aeinae.plateful.utils;

import com.aeinae.plateful.data.meals.model.MealDto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MealUtils {
    public static List<String> getIngredients(MealDto meal) {
        List<String> ingredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            try {
                Field ingredientField = MealDto.class.getDeclaredField("strIngredient" + i);
                ingredientField.setAccessible(true);
                String ingredient = (String) ingredientField.get(meal);
                if (ingredient != null && !ingredient.trim().isEmpty()) {
                    ingredients.add(ingredient.trim());
                } else {
                    break;
                }
            } catch (Exception ignored) {}
        }
        return ingredients;
    }

    public static List<String> getMeasures(MealDto meal) {
        List<String> measures = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            try {
                Field measureField = MealDto.class.getDeclaredField("strMeasure" + i);
                measureField.setAccessible(true);
                String measure = (String) measureField.get(meal);
                if (measure != null && !measure.trim().isEmpty()) {
                    measures.add(measure.trim());
                } else {
                    break;
                }
            } catch (Exception ignored) {}
        }
        return measures;
    }

}
