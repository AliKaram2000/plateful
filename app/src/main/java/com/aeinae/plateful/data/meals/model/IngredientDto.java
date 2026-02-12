package com.aeinae.plateful.data.meals.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class IngredientDto {
    @SerializedName("idIngredient")
    private String idIngredient;

    @SerializedName("strIngredient")
    private String strIngredient;

    @SerializedName("strDescription")
    private String strDescription;

    @SerializedName("strThumb")
    private String strThumb;

    @SerializedName("strType")
    private String strType;
}