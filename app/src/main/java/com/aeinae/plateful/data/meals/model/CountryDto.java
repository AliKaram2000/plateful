package com.aeinae.plateful.data.meals.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CountryDto {
    @SerializedName("strArea")
    private String strArea;
}