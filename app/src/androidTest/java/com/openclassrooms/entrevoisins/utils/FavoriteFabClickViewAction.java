package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

/**
 * Created by stéphane Warin OCR on 11/03/2021.
 */
public class FavoriteFabClickViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on FAB please";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.add_Favorite);
        button.performClick();
    }
}
