package com.eineao.travelar;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacilitiesFragment extends Fragment {

    private String[] mFoodPlaces, mRestPlaces;
    private ArrayList<CardView> mCards;
    private View mFragment;

    public FacilitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragment = inflater.inflate(R.layout.fragment_facilities, container, false);

        mFoodPlaces = new String[]{
                "Baskin Robbins", "Burger King", "Costa", "Dominos Pizza",
                "Dunkin Donuts", "KFC", "Mcdonalds"
        };
        mRestPlaces = new String[]{"Toilets", "Taxi", "WiFi", "Bed", "Hotel Room", "Television", "GYM"};
        mCards = new ArrayList<>();

        populateView((LinearLayout) mFragment.findViewById(R.id.food), mFoodPlaces);
        populateView((LinearLayout) mFragment.findViewById(R.id.rest), mRestPlaces);
        // Inflate the layout for this fragment
        return mFragment;
    }

    private void populateView(LinearLayout layout, String[] resources) {
        for (String resource : resources) {
            View cardLayout = getActivity().getLayoutInflater().inflate(R.layout.card_item_view, null);
            ((ImageView) cardLayout.findViewById(R.id.card_image))
                    .setImageResource(getResources()
                            .getIdentifier(resource.replace(' ', '_')
                                    .toLowerCase(), "drawable", getActivity().getPackageName()));
            ((TextView) cardLayout.findViewById(R.id.card_title)).setText(resource);

            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            params.setMargins(4, 0, 4, 0);

            cardLayout.setLayoutParams(params);
            layout.addView(cardLayout);

            CardView cardView = (CardView) cardLayout.findViewById(R.id.card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    for (CardView card : mCards)
                        card.setCardBackgroundColor(Color.WHITE);

                    ValueAnimator colorAnim = ObjectAnimator.ofInt(v, "cardBackgroundColor", Color.WHITE, 0xFFD8F0F4);
                    colorAnim.setDuration(500);
                    colorAnim.setEvaluator(new ArgbEvaluator());
                    colorAnim.start();
                }
            });
            mCards.add(cardView);
        }
    }
}
