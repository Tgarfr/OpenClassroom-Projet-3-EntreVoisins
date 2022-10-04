package com.openclassrooms.entrevoisins.utils;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.allOf;

import android.support.design.widget.TabLayout;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

public class TabLayoutSelectAction implements ViewAction {

    int tabIndex;

    public TabLayoutSelectAction(int position) {
        tabIndex = position;
    }

    @Override
    public Matcher<View> getConstraints() {
        return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
    }

    @Override
    public String getDescription() {
        return "Select Menu Action";
    }

    @Override
    public void perform(UiController uiController, View view) {
        TabLayout tabLayout = (TabLayout) view;
        TabLayout.Tab tabAtIndex = tabLayout.getTabAt(tabIndex);
        tabAtIndex.select();
    }
}