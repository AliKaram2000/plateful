package com.aeinae.plateful.search.presenter;

import com.aeinae.plateful.data.meals.repository.MealsRepository;
import com.aeinae.plateful.search.view.SearchMealsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    MealsRepository repo;
    SearchMealsView searchMealsView;
    public SearchPresenter(SearchMealsView searchMealsView){
        this.searchMealsView = searchMealsView;
        repo = new MealsRepository();
    }
    public void searchAllMeals(String searchMeal){
        Disposable disposable = repo.getAllMeals(searchMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals->{
                            searchMealsView.updateMealsList(meals.getMeals());
                        },
                        t->{
                            searchMealsView.displayError();
                        }
                );
    }

}
