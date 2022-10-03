package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.ClickOnNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repository.FavoriteNeighbourIdRepository;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class FavoriteNeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    FavoriteNeighbourIdRepository favoriteNeighbourIdRepository;

    /**
     * Create and return a new instance
     * @return @{@link FavoriteNeighbourFragment}
     */
    public static FavoriteNeighbourFragment newInstance() {
        FavoriteNeighbourFragment fragment = new FavoriteNeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        favoriteNeighbourIdRepository = FavoriteNeighbourIdRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        List<Neighbour> displayList = this.getFavoriteNeighboursList();
        mRecyclerView.setAdapter(new MyFavoriteNeighbourRecyclerViewAdapter(displayList));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private List<Neighbour> getFavoriteNeighboursList() {
        List<Neighbour> neighboursObjetList = new ArrayList<Neighbour>();
        for (int i = 0; i < favoriteNeighbourIdRepository.countNeighbour(); i++) {
            Neighbour addNeighbour = mApiService.getNeighbourWithId(favoriteNeighbourIdRepository.getIdList().get(i));
            neighboursObjetList.add(addNeighbour);
        }
        return neighboursObjetList;
    }

    @Subscribe
    public void clickOnNeighbour(ClickOnNeighbourEvent event) {
        DetailNeighbourActivity.navigate(getActivity(), event.neighbour);
        EventBus.getDefault().cancelEventDelivery(event);
    }
}