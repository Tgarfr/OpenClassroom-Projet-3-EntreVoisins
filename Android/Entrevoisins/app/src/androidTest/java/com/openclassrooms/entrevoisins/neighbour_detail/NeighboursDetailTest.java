package com.openclassrooms.entrevoisins.neighbour_detail;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class NeighboursDetailTest {


    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void myNeighboursDetail_clickOnNeighboursList() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(ViewMatchers.withId(R.id.detailNeighbourActivity))
                .check(matches(isDisplayed()));
    }

    @Test
    public void myNeighboursDetail_nameOnNeighboursDetailActivity() {

        // Given : On the TESTEDLINE, The name of Neighbour is nameNeighbourTest
        final int TESTEDLINE = 0;
        List<Neighbour> NeighbourList = DI.getNewInstanceApiService().getNeighbours();
        String nameNeighbourTest = NeighbourList.get(TESTEDLINE).getName();

        // When : Perform a click on a neighbour item
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(TESTEDLINE, click()));

        // Then : the name of de neighbour is the same to the neighbour clicked
        onView(ViewMatchers.withId(R.id.nameTitreDetail))
                .check(matches(withText(containsString(nameNeighbourTest))));
    }

}