package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.ClickOnNeighbourEvent;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ClickOnNeighbourEvent clickOnNeighbourEvent;

    public ListNeighbourPagerAdapter(FragmentManager fm, ClickOnNeighbourEvent activityClickOnNeighbourEvent) {
        super(fm);
        clickOnNeighbourEvent = activityClickOnNeighbourEvent;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        NeighbourApiService neighbourApiService = null;
        if(position == 1) {
            neighbourApiService = ListNeighbourActivity.neighbourFavoritesList;
        } else {
            neighbourApiService = DI.getNeighbourApiService();
        }
        return NeighbourFragment.newInstance(neighbourApiService, clickOnNeighbourEvent);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}