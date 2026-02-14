package com.aeinae.plateful.details.view;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import java.util.Calendar;
import java.util.List;


public class DetailsFragment extends Fragment implements DetailsView {
    DetailsPresenter presenter;
    TextView mealName;
    private ImageView mealImage;
    private TextView mealCategory;
    private TextView mealOrigin;
    private LinearLayout ingredientsContainer;
    private TextView mealInstructions;
    private YouTubePlayerView youtubePlayerView;
    private ImageView addPlannedIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        addPlannedIcon.setOnClickListener(v->{
            addPlannedMeal(meal);
        });
        ingredientsContainer.removeAllViews();
        int count = Math.min(ingredients.size(), measures.size());
        for (int i = 0; i < count; i++) {
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
//                Log.d("Video ID:", videoId);
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
        if (url.contains("v=")) {
            String[] parts = url.split("v=");
            String idPart = parts[1];
            int ampIndex = idPart.indexOf("&");
            return ampIndex != -1 ? idPart.substring(0, ampIndex) : idPart;
        }
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
        addPlannedIcon = view.findViewById(R.id.ic_add_planned);
    }
    private void addPlannedMeal(MealDto meal){
        Calendar now = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {

                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, dayOfMonth, 0, 0, 0);
                    cal.set(Calendar.MILLISECOND, 0);

                    long selectedDay = cal.getTimeInMillis();

                    presenter.insertPlannedMeal(meal, selectedDay);
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dialog.getDatePicker().setMinDate(System.currentTimeMillis());

        dialog.show();
    }
}