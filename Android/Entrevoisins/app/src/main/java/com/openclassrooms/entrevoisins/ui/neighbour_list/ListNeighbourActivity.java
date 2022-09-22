package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddFavoriteNeighbour;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourIdList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity implements AddFavoriteNeighbour {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;
    public static FavoriteNeighbourIdList neighbourFavoritesList = new FavoriteNeighbourIdList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager(), neighbourFavoritesList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }

    @Override
    public void clickAddFavorite(Neighbour neighbourAdd) {
        neighbourFavoritesList.addIdNeighbour(neighbourAdd.getId());
    }
}

