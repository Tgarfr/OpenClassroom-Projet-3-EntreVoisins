package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.Arrays;
import java.util.List;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private final List<Fragment> fragments = Arrays.asList(NeighbourFragment.newInstance(), FavoriteNeighbourFragment.newInstance());

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void onPageSelected(int i) {
        Fragment fragment = fragments.get(i);
        fragment.onResume(); // FIXME Use FragmentPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT instead, but this requires to migrate to Androidx
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {}

    @Override
    public void onPageScrollStateChanged(int i) {}
}