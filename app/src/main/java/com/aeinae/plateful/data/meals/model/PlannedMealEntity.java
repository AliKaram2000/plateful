package com.aeinae.plateful.data.meals.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "planned_meal")
public class PlannedMealEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long plannedId;
    private String id;
    private String title;
    private String category;
    private String country;
    private String countryFlagUrl;
    private String instructions;
    private String thumbnail;
    private String youtubeUrl;
    long date;
    public PlannedMealEntity(@NonNull String id,
                       String title,
                       String category,
                       String country,
                       String countryFlagUrl,
                       String instructions,
                       String thumbnail,
                       String youtubeUrl,
                       long date)
    {
        this.id = id;
        this.title = title;
        this.category = category;
        this.country = country;
        this.countryFlagUrl = countryFlagUrl;
        this.instructions = instructions;
        this.thumbnail = thumbnail;
        this.youtubeUrl = youtubeUrl;
        this.date = date;
    }
}
