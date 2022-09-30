package com.openclassrooms.entrevoisins.neighbour_list;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repository.FavoriteNeighbourIdRepository;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.RecyclerViewItemAssertion;
import com.openclassrooms.entrevoisins.utils.TabLayoutSelectAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursFavoriteListTest {


    private ListNeighbourActivity mActivity;
    private FavoriteNeighbourIdRepository favoriteNeighours;
    private List<Neighbour> neighboursList;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        favoriteNeighours = FavoriteNeighbourIdRepository.getInstance();
        neighboursList = DI.getNewInstanceApiService().getNeighbours();

        // If not favorites neighbour, create 3 favorite neighbour
        if (favoriteNeighours.countNeighbour() == 0) {
            final int addedFavoriteNeighbours = 3;
            int neighboursListSize = neighboursList.size();
            for (int i = 0; i < addedFavoriteNeighbours & i < neighboursListSize; i++) {
                onView(withId(R.id.list_neighbours)) .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
                onView(withId(R.id.add_favorite_neighbour)) .perform(click());
                onView(withId(R.id.backButtonDetail)) .perform(click());
            }
        }
        onView(withId(R.id.tabs)).perform(new TabLayoutSelectAction(1));
    }

    @Test
    public void myNeighboursFavoriteList_shouldNotBeEmpty() {
        onView(allOf(ViewMatchers.withId(R.id.list_favorite_neighbours)))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myNeighboursFavoriteListTest_checkList() {
        ViewInteraction view = onView(withId(R.id.list_favorite_neighbours));
        for (int i = 0; i < favoriteNeighours.countNeighbour(); i++) {
            Long favoriteNeighbourId = favoriteNeighours.getIdList().get(i);
            Neighbour favoriteNeighbour = favoriteNeighours.getNeighboursFromList(favoriteNeighbourId, neighboursList);
            view.check(new RecyclerViewItemAssertion(i, R.id.item_list_name, withText(favoriteNeighbour.getName())));
        }

    }
}