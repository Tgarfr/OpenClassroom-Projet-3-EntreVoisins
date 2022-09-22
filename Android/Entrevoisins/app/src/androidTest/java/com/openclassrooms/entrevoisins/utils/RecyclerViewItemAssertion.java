package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;


public class RecyclerViewItemAssertion implements ViewAssertion {
    int itemPosition;
    int testedViewId;
    Matcher<View> matcher;

    public RecyclerViewItemAssertion(int itemPosition, int testedViewIdInItem, Matcher<View> matcher) {
        this.itemPosition = itemPosition;
        this.testedViewId = testedViewIdInItem;
        this.matcher = matcher;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForLayoutPosition(itemPosition);
        View childView = viewHolder.itemView.findViewById(testedViewId);
        ViewMatchers.assertThat(childView, matcher);
    }
}