package com.aeinae.plateful.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.search.presenter.SearchPresenter;
import com.google.android.material.chip.Chip;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;


public class SearchMealsFragment extends Fragment implements SearchMealsView {
    private SearchView searchBar;
    private Chip chipCategory;
    private Chip chipIngredient;
    private Chip chipCountry;
    private RecyclerView recyclerView;
    private SearchMealsAdapter adapter;
    private String searching;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final PublishSubject<String> searchSubject = PublishSubject.create();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        SearchPresenter presenter = new SearchPresenter(this, this.requireActivity().getApplication());
        recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        );
        adapter = new SearchMealsAdapter();
        recyclerView.setAdapter(adapter);
        observeSearchEditText(presenter);
        adapter.notifyDataSetChanged();
        adapter.setOnFavoriteClickListener(meal -> {
            Toast.makeText(requireContext(),
                    "Meal added to Favorites: " + meal.getStrMeal(),
                    Toast.LENGTH_SHORT).show();
        });
        adapter.setOnSearchItemClickListener(id->{
            NavDirections action = SearchMealsFragmentDirections.actionSearchFragmentToDetailsFragment(id);
            NavController controller = NavHostFragment.findNavController(this);
            controller.navigate(action);
        });
    }
    @Override
    public void updateMealsList(List<MealDto> meals) {
        adapter.setMealsList(meals);
    }
    @Override
    public void displayError() {
        Toast.makeText(getContext(), "Failed to load meal", Toast.LENGTH_SHORT).show();
    }
    private void observeSearchEditText(SearchPresenter presenter) {

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchSubject.onNext(query.trim());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchSubject.onNext(newText.trim());
                return true;
            }
        });

        disposables.add(
                searchSubject
                        .debounce(400, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .filter(query -> !query.isEmpty())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(query -> {
                            searching = query;
                            presenter.searchAllMeals(query);
                        },
                t->{
                            Toast.makeText(requireContext(), "Searching failed", Toast.LENGTH_SHORT).show();
                                }
                        )

        );
    }

    public void bindView(View view){
        searchBar = view.findViewById(R.id.search_bar);
        chipCategory = view.findViewById(R.id.chip_category);
        chipIngredient = view.findViewById(R.id.chip_ingredient);
        chipCountry = view.findViewById(R.id.chip_country);
        recyclerView = view.findViewById(R.id.recyclerViewSearch);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear();
    }

}