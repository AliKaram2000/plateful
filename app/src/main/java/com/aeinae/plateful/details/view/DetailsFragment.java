package com.aeinae.plateful.details.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.details.presenter.DetailsPresenter;
import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;


public class DetailsFragment extends Fragment implements DetailsView {

    DetailsPresenter presenter;
    TextView mealName;
    private ImageView mealImage;
    private TextView mealCategory;
    private TextView mealOrigin;
    private LinearLayout ingredientsContainer;
    private TextView mealInstructions;
    private YouTubePlayerView youtubePlayerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        presenter = new DetailsPresenter(this, this.requireActivity().getApplication());
        String mealID = com.aeinae.plateful.details.view.DetailsFragmentArgs.fromBundle(getArguments()).getIdMeal();
        presenter.getMealDetails(Integer.parseInt(mealID));
        Log.e("KABSE", mealID);
    }

    @Override
    public void displayMealDetails(MealDto meal, List<String> ingredients, List<String> measures) {
        Glide.with(mealImage)
                .load(meal.getStrMealThumb())
                .into(mealImage);
        mealName.setText(meal.getStrMeal());
        mealCategory.setText(meal.getStrCategory());
        mealOrigin.setText(meal.getStrArea());
        mealInstructions.setText(meal.getStrInstructions());
        ingredientsContainer.removeAllViews();
        for (int i = 0; i < ingredients.size(); i++) {
            TextView textView = new TextView(requireContext());
            textView.setText("• " + measures.get(i) + " " + ingredients.get(i));
            textView.setTextSize(16);
            textView.setPadding(0, 4, 0, 4);
            ingredientsContainer.addView(textView);
        }

        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoUrl = meal.getStrYoutube();
                String videoId = extractYoutubeId(videoUrl);
                Log.d("Video ID:", videoId);
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0);
                }

            }
        });
    }

    @Override
    public void onError() {
        //TODO: Fill placeHolders
    }
    private String extractYoutubeId(String url) {
        if (url == null || url.isEmpty()) return null;

        // Standard YouTube link
        if (url.contains("v=")) {
            String[] parts = url.split("v=");
            String idPart = parts[1];
            // strip any extra params like &t=30s
            int ampIndex = idPart.indexOf("&");
            return ampIndex != -1 ? idPart.substring(0, ampIndex) : idPart;
        }

        // Short link
        if (url.contains("youtu.be/")) {
            return url.substring(url.lastIndexOf("/") + 1);
        }

        return null;
    }
    public void bindViews(View view){
        mealImage = view.findViewById(R.id.mealImage);
        mealName = view.findViewById(R.id.mealName);
        mealCategory = view.findViewById(R.id.mealCategory);
        mealOrigin = view.findViewById(R.id.mealOrigin);
        ingredientsContainer = view.findViewById(R.id.ingredientsContainer);
        mealInstructions = view.findViewById(R.id.mealInstructions);
        youtubePlayerView = view.findViewById(R.id.youtubePlayer);
        getLifecycle().addObserver(youtubePlayerView);
    }
}