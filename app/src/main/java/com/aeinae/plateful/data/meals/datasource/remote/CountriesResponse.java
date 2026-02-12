package com.aeinae.plateful.data.meals.datasource.remote;

import com.aeinae.plateful.data.meals.model.CountryDto;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CountriesResponse {
    @SerializedName("meals")
    private List<CountryDto> countries;

    public List<CountryDto> getCountries() {
        return countries == null ? List.of() : countries;
    }
}