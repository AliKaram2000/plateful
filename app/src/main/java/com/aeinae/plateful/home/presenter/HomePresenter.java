package com.aeinae.plateful.home.presenter;

import android.widget.Toast;

import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.data.meals.repository.MealsRepository;
import com.aeinae.plateful.home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    MealsRepository repo;
    HomeView homeView;
    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        repo = new MealsRepository();
    }

    public void getRandomMeal(){
        Disposable disposable = repo.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals->{
                    homeView.displayMealOfTheDay(meals.getMeals().get(0));
                },
        t->{
                    homeView.displayError();
                    });
    }
    public void getAllMeals(){
        String ALL_MEALS = "a";
        Disposable disposable = repo.getAllMeals(ALL_MEALS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals->{
                            homeView.updateMealsList(meals.getMeals());
                        },
                        t->{
                            homeView.displayError();
                        }
                );
    }

}
