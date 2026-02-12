package com.aeinae.plateful.data.meals.datasource.remote;

import com.aeinae.plateful.data.meals.model.IngredientDto;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class IngredientsResponse {
    @SerializedName("meals")
    private List<IngredientDto> ingredients;

    public List<IngredientDto> getIngredients() {
        return ingredients == null ? List.of() : ingredients;
    }
}