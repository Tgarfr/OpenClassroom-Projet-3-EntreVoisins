package com.openclassrooms.entrevoisins.repository;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighbourIdRepository {

    List<Long> neighbourFavoriteList;
    NeighbourApiService neighbourApiService;

    private FavoriteNeighbourIdRepository() {
        neighbourFavoriteList = new ArrayList<Long>();
    }

    private static FavoriteNeighbourIdRepository INSTANCE = new FavoriteNeighbourIdRepository();

    public static FavoriteNeighbourIdRepository getInstance() {
        return INSTANCE;
    }

    public void setNeighbourApiService(NeighbourApiService neighbourApiService) {
        this.neighbourApiService = neighbourApiService;
    }

    public void addIdNeighbour(Long neighbourId) {
        if ( (getNeighboursFromList(neighbourId, neighbourApiService.getNeighbours()) != null) & !neighbourFavoriteList.contains(neighbourId) ) {
            neighbourFavoriteList.add(neighbourId);
        }
    }

    public void deleteIdNeighbour(Long neighbourId) {
        neighbourFavoriteList.remove(neighbourId);
    }

    public List<Long> getIdList() {
        return neighbourFavoriteList;
    }

    public List<Neighbour> getNeighboursList() {
        List<Neighbour> neighboursObjetList = new ArrayList<Neighbour>();
        for (int i = 0; i < this.countNeighbour(); i++) {
            Neighbour addNeighbour = getNeighboursFromList(this.getIdList().get(i), neighbourApiService.getNeighbours());
            neighboursObjetList.add(addNeighbour);
        }
        return neighboursObjetList;
    }

    public int countNeighbour() {
        return neighbourFavoriteList.size();
    }

    public Neighbour getNeighboursFromList(Long neighbourId, List<Neighbour> listeNeighbour) {
        int listeNeighbourSize = listeNeighbour.size();
        for (int i = 0; i < listeNeighbourSize; i++) {
            if (listeNeighbour.get(i).getId() == neighbourId) {
                return listeNeighbour.get(i);
            }
        }
        return null;
    }
}
