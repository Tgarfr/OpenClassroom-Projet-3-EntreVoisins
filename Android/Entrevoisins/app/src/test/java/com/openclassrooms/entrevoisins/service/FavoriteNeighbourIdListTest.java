package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repository.FavoriteNeighbourIdRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class FavoriteNeighbourIdListTest {

    private FavoriteNeighbourIdRepository favoriteNeighbourIdList;
    private NeighbourApiService neighbourApiService;
    private List<Neighbour> neighbourList;

    @Before
    public void setup() {
        neighbourApiService = DI.getNewInstanceApiService();
        neighbourList = neighbourApiService.getNeighbours();
        favoriteNeighbourIdList = FavoriteNeighbourIdRepository.getInstance();

        if (favoriteNeighbourIdList.countNeighbour() == 0) {
            final int addedFavoriteNeighbours = 3;
            int neighbourListSize = neighbourList.size();
            for (int i = 0; i < addedFavoriteNeighbours & i < neighbourListSize; i++) {
                favoriteNeighbourIdList.addIdNeighbour(neighbourList.get(i).getId());
            }
        }
    }

    @Test
    public void getFavoriteNeighboursFromListWithSuccess() {
        // Arrange
        Long expectedFavoriteNeighbourId =  favoriteNeighbourIdList.getlist().get(0);

        // Act
        Neighbour favoriteNeighbour = favoriteNeighbourIdList.getFavoriteNeighboursFromList(expectedFavoriteNeighbourId, neighbourList);
        Long actual = favoriteNeighbour.getId();

        // Assert
        assertEquals(expectedFavoriteNeighbourId, actual);
    }

    @Test
    public void getFavoriteNeighboursListFromNeighboursApiWithSuccess() {
        // Arrange
        List<Long> expectedFavoriteNeighbourId = favoriteNeighbourIdList.getlist();

        // Act
        List<Neighbour> actualFavoriteNeighbourList = favoriteNeighbourIdList.getFavoriteNeighboursListFromNeighboursApi(neighbourApiService);

        // Assert
        int actualFavoriteNeighbourListSize = actualFavoriteNeighbourList.size();
        for ( int i = 0; i < actualFavoriteNeighbourListSize; i++) {
            Long expectedId = expectedFavoriteNeighbourId.get(i);
            Long actualId = actualFavoriteNeighbourList.get(i).getId();
            assertEquals(expectedId, actualId);
        }
    }

}
