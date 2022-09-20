package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.ClickOnNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighbourList;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class FavoritesNeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    public static FavoriteNeighbourList mNeighbourFavoritesList;

    /**
     * Create and return a new instance
     * @return @{@link FavoritesNeighbourFragment}
     */
    public static FavoritesNeighbourFragment newInstance(FavoriteNeighbourList neighbourFavoritesList) {
        FavoritesNeighbourFragment fragment = new FavoritesNeighbourFragment(neighbourFavoritesList);
        return fragment;
    }

    public FavoritesNeighbourFragment(FavoriteNeighbourList neighbourFavoritesList) {
        mNeighbourFavoritesList = neighbourFavoritesList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
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
        List<Neighbour> displayList = getNeighboursListFromListFavoriteId(mNeighbourFavoritesList,mApiService.getNeighbours());
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(displayList));
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

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        Log.i("TAG","debugage");
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    /*
    @Subscribe
    public void clickOnNeighbour(ClickOnNeighbourEvent event) {
        DetailNeighbourActivity.navigate(getActivity(), event.neighbour);
    }*/

    private List<Neighbour> getNeighboursListFromListFavoriteId(FavoriteNeighbourList listeFavoriteId, List<Neighbour> listeNeighbour) {
        List<Neighbour> neighboursList = new ArrayList<Neighbour>();
        int listeFavoriteIdSize = listeFavoriteId.countNeighbour();
        int listeNeighbourSize = listeNeighbour.size();
        for (int i = 0; i < listeFavoriteIdSize; i++) {
            for (int j = 0; j < listeNeighbourSize; j++) {
                if (listeNeighbour.get(j).getId() == listeFavoriteId.getlist().get(i)) {
                    neighboursList.add(listeNeighbour.get(j));
                }
            }
        }
        return neighboursList;
    }



}
