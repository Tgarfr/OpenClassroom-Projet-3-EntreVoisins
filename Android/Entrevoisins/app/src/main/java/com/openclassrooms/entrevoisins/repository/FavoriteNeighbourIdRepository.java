package com.openclassrooms.entrevoisins.repository;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighbourIdRepository {

    List<Long> neighbourFavoriteList;

    private FavoriteNeighbourIdRepository() {
        neighbourFavoriteList = new ArrayList<Long>();
    }

    private static FavoriteNeighbourIdRepository INSTANCE = new FavoriteNeighbourIdRepository();

    public static FavoriteNeighbourIdRepository getInstance() {
        return INSTANCE;
    }

    public void addIdNeighbour(Long neighbourId) {
        if (!neighbourFavoriteList.contains(neighbourId)) {
            neighbourFavoriteList.add(neighbourId);
        }
    }

    public void deleteIdNeighbour(Long neighbourId) {
        if (neighbourFavoriteList.contains(neighbourId)) {
            neighbourFavoriteList.remove(neighbourId);
        }
    }

    public List<Long> getIdList() {
        return neighbourFavoriteList;
    }

    public int countNeighbour() {
        return neighbourFavoriteList.size();
    }
}
