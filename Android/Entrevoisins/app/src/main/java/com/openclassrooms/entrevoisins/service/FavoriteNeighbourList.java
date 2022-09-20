package com.openclassrooms.entrevoisins.service;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighbourList {

    List<Long> neighbourFavoriteList;

    public FavoriteNeighbourList() {
        neighbourFavoriteList = new ArrayList<Long>();
    }

    public List<Long> getlist() {
        return neighbourFavoriteList;
    }

    public void addNeighbour(Long neighbourId) {
        neighbourFavoriteList.add(neighbourId);
    }

    public void deleteNeighbour(Long neighbourId) {
        neighbourFavoriteList.remove(neighbourId);
    }

    public int countNeighbour() {
        return neighbourFavoriteList.size();
    }
}
