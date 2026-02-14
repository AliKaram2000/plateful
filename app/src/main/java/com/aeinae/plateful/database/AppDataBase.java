package com.aeinae.plateful.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.model.PlannedMealEntity;

@Database(entities = {MealEntity.class, PlannedMealEntity.class}, version = 5)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "meals.db";
    private static volatile AppDataBase instance;
    public static AppDataBase getInstance(Application application) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(application, AppDataBase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
    public abstract MealDao getMealDao();
    public abstract PlannedMealDao getPlannedMealDao();
}
