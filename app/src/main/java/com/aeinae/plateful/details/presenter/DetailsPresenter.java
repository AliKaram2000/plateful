package com.aeinae.plateful.details.presenter;

import android.app.Application;

import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.data.meals.repository.MealsRepository;
import com.aeinae.plateful.details.view.DetailsView;
import com.aeinae.plateful.utils.MealUtils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {
    MealsRepository repo;
    DetailsView detailsView;
    public DetailsPresenter(DetailsView detailsView, Application application){
        this.detailsView = detailsView;
        repo = new MealsRepository(application);
    }
    public void getMealDetails(int mealID){
        Disposable disposable = repo.getMealDetails(mealID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
            meals -> {
                MealDto meal = meals.getMeals().get(0);
                List<String> ingredients = MealUtils.getIngredients(meal);
                List<String> measures = MealUtils.getMeasures(meal);
                detailsView.displayMealDetails(meal, ingredients, measures);
                        },
                        t->{
                            detailsView.onError();
                        }
                );
    }
    /*public Single<MealDto> getMealByID(String mealID) {
        return repo.getMealDetails(Integer.parseInt(mealID))
                .map(meals -> meals.getMeals().get(0));
    }*/
}
