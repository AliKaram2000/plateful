package com.aeinae.plateful.favorites.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.favorites.presenter.FavoritesPresenter;

import java.util.List;

public class FavoritesFragment extends Fragment implements FavoritesView {
    RecyclerView recyclerView;
    FavoriteMealsAdapter adapter;
    FavoritesPresenter presenter;

    public FavoritesFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favourites_recycler_view);
        presenter = new FavoritesPresenter(this, this.requireActivity().getApplication());
        displayFavoriteMeals();
    }

    @Override
    public void setRecyclerView(List<MealEntity> meals){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FavoriteMealsAdapter();
        adapter.setMealsList(meals);
        recyclerView.setAdapter(adapter);
        adapter.setOnRemoveFavoriteClickListener(meal -> {
            presenter.deleteFavoriteMeal(meal);
            Toast.makeText(requireContext(), "Removed "+ meal.getTitle()+ " from favorites", Toast.LENGTH_SHORT).show();
            presenter.getFavoriteMeals();
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void displayFavoriteMeals() {
        presenter.getFavoriteMeals();
    }
}