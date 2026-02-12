package com.aeinae.plateful.data.meals.datasource.remote;

import com.aeinae.plateful.data.meals.model.CategoryDto;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CategoriesResponse {
    @SerializedName("meals")
    private List<CategoryDto> categories;

    public List<CategoryDto> getCategories() {
        return categories == null ? List.of() : categories;
    }
}