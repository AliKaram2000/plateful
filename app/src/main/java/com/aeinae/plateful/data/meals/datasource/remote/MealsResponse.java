package com.aeinae.plateful.data.meals.datasource.remote;

import com.aeinae.plateful.data.meals.model.MealDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealsResponse {
    @SerializedName("meals")
    public List<MealDto> meals;

    public List<MealDto> getMeals() {
        return meals == null ? List.of() : meals;
    }
}
