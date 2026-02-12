package com.aeinae.plateful.data.meals.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "meals")
public class MealEntity {
    @NonNull
    @PrimaryKey
    private String id;
   // @NonNull
    private String title;
   // @NonNull
    private String category;
  //  @NonNull
    private String country;
   // @NonNull
    private String countryFlagUrl;
   // @NonNull
    private String instructions;
   // @NonNull
    private String thumbnail;
   // @NonNull
    private String youtubeUrl;
    public MealEntity(@NonNull String id,
                       String title,
                       String category,
                       String country,
                       String countryFlagUrl,
                       String instructions,
                       String thumbnail,
                       String youtubeUrl)
    {
        this.id = id;
        this.title = title;
        this.category = category;
        this.country = country;
        this.countryFlagUrl = countryFlagUrl;
        this.instructions = instructions;
        this.thumbnail = thumbnail;
        this.youtubeUrl = youtubeUrl;
    }
}
