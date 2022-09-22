package com.openclassrooms.entrevoisins.neighbour_list;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourIdList;
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
    private FavoriteNeighbourIdList favoriteNeighours;
    private List<Neighbour> listeNeighours;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        favoriteNeighours = (FavoriteNeighbourIdList) mActivity.neighbourFavoritesList;
        listeNeighours = DI.getNewInstanceApiService().getNeighbours();

        // If not favorites neighbour, create 3 favorite neighbour
        if (favoriteNeighours.countNeighbour() == 0) {
            final int addedFavoriteNeighbours = 3;
            int sizelisteNeighour = listeNeighours.size();
            for (int i = 0; i < addedFavoriteNeighbours & i < sizelisteNeighour; i++) {
                onView(withId(R.id.list_neighbours)) .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
                onView(withId(R.id.add_favorite_neighbour)) .perform(click());
                onView(withId(R.id.backButtonDetail)) .perform(click());
            }
        }
        onView(withId(R.id.tabs)).perform(new TabLayoutSelectAction(1));
    }


    @Test
    public void myNeighboursFavoriteListTest_checkList() {
        ViewInteraction view = onView(withId(R.id.list_favorite_neighbours));
        view.check(withItemCount(favoriteNeighours.countNeighbour()));
        for (int i = 0; i < favoriteNeighours.countNeighbour(); i++) {
            Long favoriteNeighbourId = favoriteNeighours.getlist().get(i);
            Neighbour favoriteNeighbour = favoriteNeighours.getFavoriteNeighboursFromList(favoriteNeighbourId,listeNeighours);
            view.check(new RecyclerViewItemAssertion(i, R.id.item_list_name, withText(favoriteNeighbour.getName())));
        }

    }
}