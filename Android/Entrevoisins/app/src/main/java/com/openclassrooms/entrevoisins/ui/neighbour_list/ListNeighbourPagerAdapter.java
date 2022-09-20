package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.service.FavoriteNeighbourList;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public FavoriteNeighbourList mNeighbourFavoritesList;

    public ListNeighbourPagerAdapter(FragmentManager fm, FavoriteNeighbourList neighbourFavoritesList) {
        super(fm);
        mNeighbourFavoritesList = neighbourFavoritesList;;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return FavoritesNeighbourFragment.newInstance(mNeighbourFavoritesList);
        }
        else {
            return NeighbourFragment.newInstance();
        }
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