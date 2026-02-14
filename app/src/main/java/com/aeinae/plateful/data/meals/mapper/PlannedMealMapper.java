package com.aeinae.plateful.data.meals.mapper;

import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.data.meals.model.PlannedMealEntity;

public class PlannedMealMapper {

    public static PlannedMealEntity toEntity(MealDto mealDto, long date) {
        if (mealDto == null) return null;

        return new PlannedMealEntity(
                String.valueOf(date),
                mealDto.getStrMeal(),
                mealDto.getStrCategory(),
                mealDto.getStrArea(),
                null,
                mealDto.getStrInstructions(),
                mealDto.getStrMealThumb(),
                mealDto.getStrYoutube(),
                date
        );
    }

    public static MealDto toDto(PlannedMealEntity entity) {
        if (entity == null) return null;

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