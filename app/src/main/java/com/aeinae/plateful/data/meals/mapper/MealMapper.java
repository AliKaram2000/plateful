package com.aeinae.plateful.data.meals.mapper;

import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.data.meals.model.MealEntity;

public class MealMapper {
    public static MealEntity toEntity(MealDto dto) {
        return new MealEntity(
                dto.getIdMeal(),
                dto.getStrMeal(),
                dto.getStrCategory(),
                dto.getStrArea(),
                "", // countryFlagUrl if you have it elsewhere
                dto.getStrInstructions(),
                dto.getStrMealThumb(),
                dto.getStrYoutube()
        );
    }

    public static MealDto toDto(MealEntity entity) {
        MealDto dto = new MealDto();
        dto.setIdMeal(entity.getId());
        dto.setStrMeal(entity.getTitle());
        dto.setStrCategory(entity.getCategory());
        dto.setStrArea(entity.getCountry());
        dto.setStrInstructions(entity.getInstructions());
        dto.setStrMealThumb(entity.getThumbnail());
        dto.setStrYoutube(entity.getYoutubeUrl());
        return dto;
    }

}
