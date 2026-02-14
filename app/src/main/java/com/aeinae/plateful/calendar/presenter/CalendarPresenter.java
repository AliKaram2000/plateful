package com.aeinae.plateful.calendar.presenter;

import android.app.Application;
import android.util.Log;

import com.aeinae.plateful.calendar.view.CalenderMealsView;
import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.model.PlannedMealEntity;
import com.aeinae.plateful.data.meals.repository.MealsRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    MealsRepository repo;
    CalenderMealsView calenderMealsView;

    public CalendarPresenter(CalenderMealsView calenderMealsView, Application application){
        this.calenderMealsView = calenderMealsView;
        repo = new MealsRepository(application);
    }
    public void getPlannedMeals(long date){
        Disposable disposable =repo.getAllPlannedMeals(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals-> {
                            calenderMealsView.setRecyclerView(meals);
                            Log.d("getfavtest",meals.toString());
                        },
                        t -> {
                            Log.d("getfavtest", t.getMessage());
                        }
                );
    }
    public void deletePlannedMeal(PlannedMealEntity meal){
        repo.removePlanedMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
